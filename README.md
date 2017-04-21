**Eeny-meeny-miny-moe problem**

**Task:**
 
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

**Solution:**

The problem is quite closely resembles well known Josephus problem.

Game requires data structure to store children and should allow following operations:

1) Count / Skip K positions
2) Retrieve Child at current position 
3) Remove Child at current position

Data structure should support quick traversals and retrieval, remove operations. There are different implications on
performance and memory depending on the datastructure used.

1) Array lists. 

Array lists is backed by arrays. This structue is optimised for O(1) retrieval at K index position.
Sequencial traversal is O(1). Removal of the elements is costly

Complexity is O(n^2)

2) Linked lists (or Circular list - doubly linked list with return to first element when last reached).

This structure is backed by list of linked nodes. Sequencial traversal is O(1). Removal is O(1). Insertion is O(1). 
Index at K position look up is O(K)

Complexity is O(kn)



-------
Explore on:

3) Trees-like index structures optimised for traversals and removal of elements. 

Complexity is O(n log n)


As initial implementation I've decided to use the reasonably simple solution (as well easy to understand). 
After some of the questions are answered solution can be optimised to suite specific need.

*Some considerations before we identify optimisations:*

* Speed to produce the whole sequence. (Batch processing)
* Speed to retrieve following value. (Realtime feed)
* How large can k and n  be. 
* What are the average values of k , n (do we need to solve more generic or specific problem optimised for average values )
