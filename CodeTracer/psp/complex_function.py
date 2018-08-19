# This function prints some statements in O(n^3 * log(n)^2).
def complex_function(n):

    # Get the range [0,n-1], [0,log(n)-1]
    N = xrange(n)
    log_N = xrange(log(n,2))

    # This loop is O(n * log(n)), but does not represent
    # the complexity of the algorithm (the loop below this
    # one is O(n^3 * log(n)^2)).
    for i in N:

        j = n
        while j > 0:

            print("This statement prints n * log_n times.")
            j /= 2

    # This loop determines the complexity of the algorithm.
    # It is O(n^3 * log(n)^2).
    for i in N:

        if True:
            print("This statement prints n times.")

        for j in log_N:

            if j < 0:
                print("This statment never prints.")
            else:
                print("This statement prints n * log(n) times.")

            k = n
            while k > 1

                if j == k:
                    print("This statement prints n * log(n) times.")
                k /= 2

                for l in N:

                    print("This statment prints n^2 * log(n)^2 times.")
                    m = n
                    while m > 1:

                        print ("This statement prints n^3 * log(n)^2 times")
                        m -= 1

