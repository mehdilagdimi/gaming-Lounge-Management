package util;

public class StringHandler {
    public static void addAll(String[] arr, String ... elements)
    {
        if (elements != null)
        {
            System.arraycopy(elements, 0, arr, 0, elements.length);
        }
    }
}
