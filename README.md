# Eeny-meeny-miny-moe

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

# Realtime / Batch

Solution required to produce a winner and elimination sequence as result. Based on this information assumption was made that it is required to produce batch result 
and not to be optimised for real-time stream of elimination elements. Although it is possible to modify routines to deliver stream of elements in asynchronous fashion when elimination results become available.
We still can make an assessment for each proposed solution if it will have better real-time response (next result is available in defined time) or should be used for batch result calculation.

# Parallelism

This problem solved by N sequential iterations and can't be executed on multiple parallel threads as every subsequent step depends on the outcome of previous.
There might be some optimisations done but in general it is not a Map/Reduce problem. Some conditions of the problem may need to be relaxed to allow parallelism. 
For example: group of N divided to groups of K then same problem applied to every of those groups, 
then result combined and problem applied again to a result to reduce the set to a single winner.

# Memory impact

Simulation in general require to keep following data structures: N children, N children eliminated. There could be variations depending on the implementation / optimisations.
One node in memory roughly estimated to occupy 40 bytes for LinkedList (2 pointers, one Integer, some overhead)

Since actual values range for N, K is not defined (can be anything) below is an attempt to classify problem from memory perspective:

* For N small enough to fit in memory (e.g: 2 * N * 40 = 10th of GB) - in memory data structures can be used
for example 32GB will be equal to storing N ~ 430 million elements
* For large N that do not fit in memory (e.g: 2 * N * 40 = more than 128 GB?) - data may need to be partially cached in memory and mostly stored on disk.


## Scenario: N is small enough to fit in memory

In memory data structure should support quick random look up, remove operations.
Different performance for those operations reflects on CPU cycles and memory performance. It is briefly discussed for each of the data structures.

### Array list

Array list is backed by arrays. This structure is optimised for O(1) look up at K index position. 
However removal of the elements is expensive O(N) operation. 

As remove operation is used many times on every iterations of N elements overall complexity is O(N^2).

This structure / algorithm works fine for small N, large K.
Each removal of element triggers resizing of array. Resizing of array is done by creating a copy. 
It utilises max of 2 * N elements storage at every step. Doing it for large sets for every of N iterations is quite expensive in terms of total CPU cycles 
(as well creates memory waste which is usually not a big problem for GC but still requires CPU).
Removal of elements at every iteration is the reason algorithm performs poorly for large N.

### Linked list

Or slightly more convenient Circular list. Circular List is doubly linked list that jumps to first element of sequence after last element was reached and next element requested.

This structure is backed by chain of linked elements. 
Look up by index at K position is expensive O(K) operation (as it requires skipping K positions). 
Removal of elements is optimised O(1) operation. 

As look up by index K is used for N iterations overall complexity is O(KN)

This structure works well for large N, small K as average complexity works out to be close to O(N) for small K
This structure as well does not have as much impact on memory / does not produce as much waste compared to an Array List as removal operation simply 
re-links A-B-C chain to A-C when B removed requiring to GC element B only.

**TODO: More advanced data structures:**

3) Trees-like index structures optimised for traversals and removal of elements. 

Complexity is O(n log n)


### Implementation of 1 and 2

As initial implementation I've decided to implement set of tests that can be used to evaluate solution. 
And implemented (1), (2) - used reasonably simple solution as well easy to understand. 

### Testing

Unit tests were executed to verify correctness for different N, K less than 50 (please refer to GameTest.java). Both 1 and 2 passed number of small tests
Long running tests were defined to mainly estimate time required for completion and have basic understanding of performance. Those tests exist in
individual Game*ImplTest classes and were annotated with @Ignore as they are technically are not Unit tests and exist for experimentation / analysis purpose. 

Both solution performed similar for small N/K (<100) and tests returned results in milliseconds time.

Array list solution for large N test was aborted as it ran over 30m long time.

Several tests for Linked list solution were executed for same large N, while K for subsequent test increased by same order of magnitude. 
Tests produced roughly proportional increase in time required to complete the test:

    N = 21,474,836  K = 1 runs around 17 seconds
    N = 21,474,836  K = 10 runs around 23 seconds
    N = 21,474,836  K = 100 runs around 1m 40 seconds
    N = 21,474,836  K = 1000 runs around 15m

## Scenario: N is too large to fit in memory

If we take it even further and generalise problem for N that is too large to fit in memory. In this case data can be partly offloaded to disk or handled completely in DB (depending on the N). 
DB is capable of inserting, updating, removing elements, able to build/rebuild indexes. As discussed earlier, solution to this problem can't be described in map-reduce way and does not scale 
horizontally on multiple servers. Thus very large datasets will run potentially many hours / days on single server. 
Before attempting to solve this in map/reduce way it would require to reduce some of the problems constraints to allow parallelism.  
