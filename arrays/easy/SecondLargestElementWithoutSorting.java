package arrays.easy;
public class SecondLargestElementWithoutSorting {

    /*
    =====================================================================================
    PROBLEM: FIND THE SECOND LARGEST ELEMENT IN AN ARRAY (WITHOUT SORTING)
    -------------------------------------------------------------------------------------
    Given an array of integers, the task is to find the second largest distinct element
    without sorting the array. Sorting would increase time complexity unnecessarily.

    The optimal approach involves scanning the array once while keeping track of the
    largest and second largest elements. This method works efficiently in linear time
    and constant space.
    =====================================================================================
    */

    public static void main(String[] args) {

        int[] arr = {12, 35, 1, 10, 34, 1};

        int secondLargest = findSecondLargest(arr);

        if (secondLargest == Integer.MIN_VALUE)
            System.out.println("Second largest element does not exist");
        else
            System.out.println("Second largest element: " + secondLargest);
    }

    /*
    =====================================================================================
    FUNCTION: findSecondLargest
    -------------------------------------------------------------------------------------
    This function finds the second largest distinct element in the array.

    Logic:
    • Maintain two variables: largest and secondLargest
    • Traverse the array once
    • Update largest when a bigger element is found
    • Update secondLargest accordingly
    • Ignore duplicate values of the largest element

    Time Complexity  : O(n)
    Space Complexity : O(1)
    =====================================================================================
    */
    static int findSecondLargest(int[] arr) {

        if (arr.length < 2)
            return Integer.MIN_VALUE;

        int largest = Integer.MIN_VALUE;
        int secondLargest = Integer.MIN_VALUE;

        for (int num : arr) {

            if (num > largest) {
                secondLargest = largest;
                largest = num;
            } else if (num > secondLargest && num != largest) {
                secondLargest = num;
            }
        }

        return secondLargest;
    }
}
