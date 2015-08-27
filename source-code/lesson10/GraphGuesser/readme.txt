There are four graphs in this project.  Write code for each of the "generator" methods in
the Generator class to try to get as close to the graphs.  The graphs were made 
with 12,000 trials.

None of the generator methods need conditionals.  They are all written using common
mathematical operations on one or more random numbers.


Graph 1:
  - You've seen this code before.
  

Graph 3:
  - This one is kind of hard.  You want to use "Math.sqrt()", which takes the 
    square root of a number and returns a double.  And remember, when you convert
    a double into an int, you basically throw away everything after the decimal point.
    
Graph 4: 
  - This graph is a close approximation of standard bell shaped curve (or,
    normal distribution). It is, actually, a "binomial distribution", which is 
    a big hint!
        Another hint: think about these two generators, and how the shapes they make
    are getting closer to the normal distribution:
        randGen.nextInt(12)
        randGen.nextInt(6) + randGen.nextInt(6)
    What would be the logical extension of this pattern?