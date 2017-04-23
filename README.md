# Eeny-meeny-miny-moe

### Prerequisites

Project is using gradle build tool to build jar artifact, execute tests and other tasks. 
Code requires JDK 1.8 to compile.

In order to execute solution from command line please run following steps from the project folder:

Build jar using gradle wrapper:

    ./gradlew clean fatJar

Or alternatively just gradle (must be installed):
    
    ./gradle clean fatJar

then execute the solution (below we pass N=6 and K=2):

    java -jar build/libs/eeny-meeny-miny-moe-all-1.0-SNAPSHOT.jar 6 2

Output should be similar to:

    Winner is: 5
    Elimination sequence is: 2 4 6 3 1


## Problem:
 
Consider the following children’s game:
 
    * n children stand around a circle. 
    * Starting with a given child and working clockwise, each child gets a 
     sequential number, which we will refer to as it’s id. 
    * Then starting with the first child, they count out from 1 until k. The 
     k’th child is now out and leaves the circle. The count starts again 
     with the child immediately next to the eliminated one.
    * Children are so removed from the circle one by one. The winner is the 
     child left standing last.
 
Write a method on a new class, which, when given n and k, returns the 
sequence of children as they go out, and the id of the winning child. Create any
additional classes, tests, etc, you need to support the design of your solution.
 
Please document design decisions you have made i.e. general approach, 
where and why you have sacrificed performance for maintainability or visa versa etc.

## Solution:

The problem is quite closely resembles well known **_Josephus problem_**.
Game simulation can be described as follows:

1) Initialize N children data structure
2) Retrieve Child at position K
3) Store Child in elimination sequence
4) Remove Child at position K
5) Repeat from step (2)

Following are the key operations used by game simulation on main data structure:

1) Retrieve Child at position K
2) Remove Child at position K

## Problem classification analysis

### Realtime / Batch

Solution required to produce a winner and elimination sequence as a result. Based on this information assumption was made that it is required to produce answer as result of batch processing rather than real-time stream of elimination elements and winner. Although, it is possible to modify routines to deliver stream of elements in asynchronous fashion when elimination results become available.

### Parallelism

This problem solved by N sequential iterations and not optimised to run on multiple parallel threads as every subsequent step depends on the outcome of previous.
There might be some optimisations done but in general it is not a Map/Reduce problem. Some conditions of the problem may need to be relaxed to allow parallelism. 
For example: group of N divided to groups of K then same problem applied to every of those groups, 
then result combined and problem applied again to a result to reduce the set to a single winner.

### Memory impact

Simulation in general require to keep following data structures: N children, N children eliminated. There could be variations depending on the implementation / optimisations.
For LinkedList one node (child) in memory roughly estimated to occupy 40 bytes (2 pointers, one Integer, some overhead)

Since actual values range for N, K is not defined (can be anything) below is an attempt to classify problem from memory perspective:

* For N small enough to fit in memory (e.g: 2 * N * 40 = 10th of GB) - in memory data structures can be used
for example 32GB will be equal to storing N ~ 430 million elements
* For large N that do not fit in memory (e.g: 2 * N * 40 = more than 128 GB?) - data may need to be partially cached in memory and mostly stored on disk.


## Scenario 1: N is small enough to fit in memory

In memory data structure should support quick random look up, remove operations.
Different performance for those operations reflects on CPU cycles and memory performance. It is briefly discussed for each of the data structures.

### Array list

Array list is backed by arrays. This structure is optimised for O(1) look up at K index position. 
However removal of the elements is expensive O(N) operation. 

As remove operation is used on every iteration over N elements overall complexity of implementation using Array list is O(N^2).

This structure / algorithm works fine for small N, large K.
Each removal of element triggers resizing of array. Resizing of array is done by creating a copy. 
It utilises max of 2 * N elements storage at every step. Doing it for large sets for every of N iterations is quite expensive in terms of total CPU cycles 
(as well creates memory waste which is usually not a big problem for Garbage Collection (GC) but still requires CPU).
Removal of elements at every iteration is the reason algorithm performs poorly for large N.

### Circular list

Circular list is a slightly more convenient variation of Linked list. Circular List implementation returns to first element of a sequence after last element was reached and next element requested.

This structure is backed by a chain of linked elements. 
Look up by index at K position is expensive O(K) operation (as it requires skipping K positions). 
Removal of elements is fast O(1) operation. 

As look up by index K is used for N iterations overall complexity is O(KN)

This structure works well for large N, small K as average complexity works out to be close to O(N) for small K
This structure does not have as much impact on memory / does not produce as much waste compared to an Array List. 
Removal operation simply re-links A-B-C chain to A-C when B is being removed. And GC required to remove B only.

### TreeList

TreeList is optimised for fast traversal, insertion and remove operations at any index of the list.
From documentation for org.apache.commons.collections4.list.TreeList:

`This list implementation utilises a tree structure internally to ensure that all insertions and removals are O(log n). 
This provides much faster performance than both an ArrayList and a LinkedList where elements are inserted and removed repeatedly from anywhere in the list.`

`The following relative performance statistics are indicative of this class:`

                  get  add  insert  iterate  remove
     TreeList       3    5       1       2       1
     ArrayList      1    1      40       1      40
     LinkedList  5800    1     350       2     325
 
As algorithm requires to perform N iterations, each will retrieve and remove element from data set - complexity of implementation is O(N log N)

This structure provides best performance for out of three for large N, K


### Implementation using ArrayList, LinkedList, TreeList

Three solutions vere implemented:

* GameArrayListImpl
* GameCircularListImpl
* GameTreeListImpl

### Testing

Unit tests were executed to verify correctness for different N, K less than 50 (please refer to GameTest.java). All three implementations passed tests and performed similar for small N/K - tests returned results in milliseconds time.

Long running tests were defined to mainly estimate time required for completion and have basic understanding of performance. Those tests exist in
individual Game*ImplTest classes and were annotated with @Ignore as they are technically not Unit tests but for experimentation / analysis purpose. 

#### Large N, K tests

##### Array list
Large N test (N = 21,474,836  K = 1) executed on Array list imlementation was aborted as it ran longer than expected (over 30m).

##### Circular list (LinkedList) 
Several tests for Linked list solution were executed for same large N, while K was increased for subsequent tests. 
Time required to complete tests increased proportionally to increase of K:

    N = 21,474,836  K = 1       took 10 s
    N = 21,474,836  K = 10      took 16 s
    N = 21,474,836  K = 100     took 57s
    N = 21,474,836  K = 1000    took 8 m 16 s

##### Tree list
Tests on Tree List implementation produced best results amongst three implementations:

    N = 21,474,836  K = 1       took 22 s
    N = 21,474,836  K = 10      took 25 s
    N = 21,474,836  K = 100     took 35 s
    N = 21,474,836  K = 1000    took 49 s
    N = 21,474,836  K = 10000   took 1m 4s
    N = 21,474,836  K = 100000  took 1m 1s

## Scenario 2: N is too large to fit in memory

In Scenario 1 we have reviewed implementations when N is small enough to fit in memory. Here we review the opposite case.

In case N is too large for data set to fit in memory, data can be partly offloaded to disk or handled completely in DB (depending on the N). 

At the same time, as discussed earlier, solution to this problem can't be described in map-reduce way and does not scale 
horizontally. Thus very large datasets will run potentially many hours / days on single a server. 
Before attempting to solve this problem in map/reduce fashion it would require to alleviate some of the problem's constraints to allow scalability.
