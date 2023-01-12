# KATTIS
Solutions for the [Kattis Problem Archive](https://open.kattis.com/)

## Hello
(getting started) 
Simple "Hello World!" sample, to play with uploading. I learned that when you do a second upload (next to your 
first one) of the same file, it gets automatically renamed. So, you need to remove the first upload,
when you one to submit a second attempt using the same file/class. This was a kind of "Duh" experience.

## 10 Kinds of People
(hard)
You get a maze and a series of queries, where the queries mean: is point 1 connected with point 2. Of course I started
with a basic BSF, how "hard" can this be? ... not! However, there appeared to be a time-limit on the challenge, which
you only see when you hit it. My standard solution didn't solve one of the test-cases in time. To make things worse ... 
you don't get to see the failing test-case nor the kindOfPeopleInput. So, you cannot validate where too much time is spend (unless 
you write additional test cases yourself) or what the specific characteristics of the failing test-case are. You simply
don't know if it fails on large kindOfPeopleInput, a difficult search or the number of queries.

So, I started with basic optimization, like using a priority-queue, no copy of the kindOfPeopleInput-maze at the kindOfPeopleInput parsing 
phase, shortcut the solution (start and endpoint must be of the right type), search both ways multi-threaded ... and 
nothing made it fast enough, and a lot of other smaller ones. After 14 failed attempts, I changed my approach.
If the number of queries is the problem, then how to eliminate overhead of that part of the process? That is possible
by grouping all adjacent zeroes and ones and assigning them the same color. Then a begin and end point, are connected 
when the have the same color. Remember, you're not asked for the path, you only need to confirm  if the points are 
connected. Now additional time is spend on color coding, but no longer on the query. That finally did the trick.

## 1-D Frogger
(Easy)
Yes, pretty easy. First submission had a compiler error (I had forgotten ro upload the FastReader class). Then one of
the tests failed, so I added some test cases and found out that I should perform the out-of-bounds checks before
checking the magic number ... DUH! Then I reordered, the checks, and another test failed. Indeed, I had the upper-bound
check was wrong. Finally the third test-run passed ... really embarrassing!

Putting all the classes into the same package is nice, but it does mean that I also had to move additional classes used
for input and output as inner classes into the main-class. I used for instance a class named ```Input``` as a value 
object to pass input data into the class doing the processing. As ```KindOfPeople``` also had an ```Input``` class, I
got a duplicate name error. I'm beginning to doubt if it was a good idea to use a single package.


