# This is the first comment
import sys
try:
    total = sum(int(arg) for arg in sys.argv[1:])

    # more comment
    print ('sum =', total)
except ValueError:
    print ('Please supply integer arguments')