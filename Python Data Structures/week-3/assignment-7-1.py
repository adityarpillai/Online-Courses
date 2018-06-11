def main():
    try:
        fname = raw_input("Enter file name: ")
        fh = open(fname)
    except:
        print "Could not open {}".format(fname)

    for line in fh:
        line = line.rstrip()
        print line.upper()

main()
