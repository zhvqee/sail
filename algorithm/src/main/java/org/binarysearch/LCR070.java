package org.binarysearch;

import java.util.HashMap;
import java.util.Map;

public class LCR070 {
    public int singleNonDuplicate(int[] nums) {
        int low=0;
        int high=nums.length-1;
        int mid=0;
        while(low<=high){
            mid=(low+high)/2;
            if(mid-1>=0 && nums[mid-1]==nums[mid]){
                if ((mid+1)%2==0){
                    low=mid+1;
                }else{
                    high=mid;
                }

            } else if(mid+1<nums.length && nums[mid+1]==nums[mid]){
                if ((nums.length-mid)%2==0){
                    high=mid-1;
                }else{
                    high=mid;
                }
            }else{
                break;
            }
        }
        return mid;
    }

    public static void main(String[] args) {
        LCR070 lcr065 = new LCR070();

        int minimumLengthEncoding = lcr065.singleNonDuplicate(new int[]{3,3,7,7,10,11,11});
        System.out.println(minimumLengthEncoding);
    }
}
