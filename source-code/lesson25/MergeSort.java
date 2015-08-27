public class MergeSort{
    public static void mergeSort (int [ ] values) {
       if (values.length <= 1) {
          // A list of 0 or 1 element is already sorted.
          return;
       } else {
          // Split the array into two equal halves.
          int [ ] leftHalf = new int [values.length/2];
          int [ ] rightHalf = new int [values.length - values.length/2];
          int k;
          for (k=0; k<leftHalf.length; k++) {
             leftHalf[k] = values[k];
          }
          for (int j=0; j<rightHalf.length; j++) {
             rightHalf[j] = values[k];
             k++;
          }
          // Sort each half.
          mergeSort (leftHalf);
          mergeSort (rightHalf);
          // Combine the two sorted halves into a single sorted whole.
          merge (leftHalf, rightHalf, values);
       }
    }
   public static void merge (int [ ] source1, int [ ] source2, int [ ] destination) {
       // Indexes into source1 and source2.
       int ks1 = 0, ks2 = 0;
       // Index into destination.
       int kd = 0;
       while (ks1<source1.length && ks2<source2.length) {
          // Copy the smaller of source1[ks1] and source2[ks2] into destination,
          // and advance the relevant index variables.
          if (source1[ks1] < source2[ks2]) {
             destination[kd] = source1[ks1];
             ks1++;
          } else {
             destination[kd] = source2[ks2];
             ks2++;
          }
          kd++;
       }
       if (ks1<source1.length) {
          // source2 ran out first.
          for (int k=ks1; k<source1.length; k++) {
             destination[kd] = source1[k];
             kd++;
          }
       }
       if (ks2<source2.length) {
          // source1 ran out first.
          for (int k=ks2; k<source2.length; k++) {
             destination[kd] = source2[k];
             kd++;
          }
       }
    }
   }

