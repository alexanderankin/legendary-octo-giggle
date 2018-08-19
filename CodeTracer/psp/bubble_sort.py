# This function sorts an array in O(n^2) time.
def bubble_sort(A):

    # Determine the number of elements 'n'.
    n = len(A)

    # Get the range [0,n-1].
    N = xrange(n)

    # For each end index [0, n-1].
    for i in N:

        # Traverse the array.
        for j in N:

            # For each neighbor in index range [0:n-1-i]...
            if (j < (n - 1 - i)):

                # ... if left neighbor is bigger than right 
                # neighbor...
                if A[j] > A[j+1]:
                    
                    # ... swap neighbors.
                    A[j], A[j+1]) = A[j+1], A[j]
    
    # Return sorted array.
    return A


