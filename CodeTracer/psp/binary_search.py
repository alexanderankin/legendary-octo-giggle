# Finds a target in an array in O(log(n) time.
def binary_search(A, target):

    # Determine the number of elements in the array.
    n = len(A)

    # Choose stop and start indices as 0 and n-1.
    start, stop = 0, n-1

    # Get the range [0, log_2(n) - 1]
    log_N = xrange(floor(log(len(A), 2))+1)

    # Perform at most log(n) iterations.
    for i in log_N:

        # Only continue search if indices are valid.
        if start < stop:

            # Calculate mid-point of start/stop indices.
            mid = (start+stop)/2

            # If the target is at index 'mid', return 'mid'.
            if A[mid] == target:
                return mid

            # Else if the value at index 'mid' is less than
            # the target, the target lies to the right of mid.
            elif A[mid] < target:
                start = mid+1

            # Else the target lise to the left of mid.
            else:
                stop = mid-1

        # If the indices are equal and point to the target,
        # return that index.
        elif start == stop and A[start] == target:
            return start

        # If indices are equal, return 
        else:
            break

    # Return -1 if target was not found.
    return -1
