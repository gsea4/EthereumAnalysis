import java.lang.reflect.*;
import java.util.*;

@SuppressWarnings("unchecked")
public class Lists{
    public static <T> void mergeSort(T[] src, T[] dest, int low, int high, Comparator<T> c){
        int length = high - low;

        if (length < 7) {
            for (int i=low; i < high; i++)
                for(int j = i; j > low && c.compare(dest[j-1], dest[j]) > 0; j--)
                    swap(dest, j, j-1);
            return;
        }

        int mid = (low+high)>>>1;
        mergeSort(dest, src, low, mid, c);
        mergeSort(dest, src, mid, high, c);

        if(c.compare(src[mid-1], src[mid]) <= 0){
            System.arraycopy(src, low, dest, low, length);
            return;
        }

        for(int i = low, p = low, q = mid; i < high; i++){
            if(q >= high || p < mid && c.compare(src[p], src[q]) <= 0)
                dest[i] = src[p++];
            else
                dest[i] = src[q++];
        }
    }

    public static <T> void sortArray(T[] a, Comparator<T> c){
        T[] aux = a.clone();
        mergeSort(aux, a, 0, a.length, c);
    }

    public static <T> void sortList(List<T> list, Comparator<T> c){
        Object[] a = list.toArray();
        sortArray(a, (Comparator)c);
        ListIterator i = list.listIterator();
        for(int j = 0; j < a.length; j++){
            i.next();
            i.set(a[j]);
        }
    }

    private static void swap(Object[] x, int a, int b) {
        Object t = x[a];
        x[a] = x[b];
        x[b] = t;
    }
}