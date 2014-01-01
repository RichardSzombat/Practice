package com.vishpat.dp;

class BadNeighbours {

    static boolean badNeighbours(int i, int j, int total) {
        boolean bad = ((i == 0 && j == (total - 1)) ||
                       (i == (total - 1) && j == 0) ||
                       (Math.abs(i - j)  == 1)); 

        return bad;
    }

    public static int getMaxDonations(int[] donations)
    {
        int max = -1;
        int i, j = 0;
        int total = donations.length;
        int maxDonations[] = new int[total];
        int maxNeighbour[] = new int[total];

        if (total == 1) {
            return donations[0];
        }

        if (total == 2) {
            return Math.max(donations[0], donations[1]);
        }

        for (i = 0; i < total; i++) {
            maxNeighbour[i] = i;
            maxDonations[i] = donations[i];
        }

        for (i = 0; i < total; i++) {
            for (j = 0; j < total; j++) {
                
                if (i == j || badNeighbours(i, j, total)) {
                    continue;
                }
                
                if ((donations[i] + maxDonations[j] > maxDonations[i]) &&
                    !badNeighbours(i, maxNeighbour[j], total)) {
    
                    maxDonations[i] = donations[i] + maxDonations[j];
                    maxNeighbour[i] = j;
                    
                    System.out.format("%d %d %d %d\n", donations[i], donations[j], maxDonations[i], maxDonations[j]);
             
                    if (maxDonations[i] > max) {
                        max = maxDonations[i];
                    }
                } 
            }
       }

       return max;
    }

    public static void main(String[] args) {
//        int donations[] = {94,40,49,65,21,21,106,80,92,81,679,4,61,6,237,12,72,74,29,95,265,35,47,1,61,397,52,72,37,51,1,81,45,435,7,36,57,86,81,72};
//        int donations[] = {1,2,3,4,5,1,2,3,4,5};
        int donations[] = {10,3,2,5,7,8};
        System.out.format("%d\n", 
                            getMaxDonations(donations));
    }
}
