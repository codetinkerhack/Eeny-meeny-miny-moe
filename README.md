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

The problem is quite closely resembles well known Josephus problem. This problem has number of solutions:

1) Simple - using arrays and resizing arrays. Complexity is O(n^2)
2) Simple - using Linked lists. Complexity is O(kn)
3) More complex - using Trees like structures optimised for traversals and removal of elements. Complexity is O(n log n)

(To verify: ) All above will perform relatively similar on modern CPU architecture. 

As initial implementation I've decided to use the reasonably simple solution (as well easy to understand). 
After some of the questions are answered solution can be optimised to suite specific need.

*Some considerations before we identify optimisations:*

* Speed to produce the whole sequence. (Batch processing)
* Speed to retrieve following value. (Realtime feed)
* How large can k and n  be. 
* What are the average values of k , n (do we need to solve more generic or specific problem optimised for average values )
