# Returns the factorial of a number in O(n) time.
def factorial(n):

    # Get the range [1, n].
    N = xrange(1, n+1)

    # Keep track of the total.
    total = 1

    # Multiply the total by each integer in the range.
    for i in N:
        total *= i

    # Return the total.
    return total