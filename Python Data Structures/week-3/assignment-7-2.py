def main():
    # Use the file name mbox-short.txt as the file name
    fname = raw_input("Enter file name: ")
    fh = open(fname)

    total = 0
    count = 0

    for line in fh:
        if not line.startswith("X-DSPAM-Confidence:") : continue
        # line starts with "X-DSPAM-Confidence"
        line = line.rstrip()
        line = line[len("X-DSPAM-Confidence: "):]
        total += float(line)
        count += 1

    print "Average spam confidence: {0:.12f}".format(total / count)

main()
