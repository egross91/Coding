import java.math.*;

public class Solution {
    public int compareVersion(String version1, String version2) {
        String v1 = filterLeadingZeroes(version1);
        String v2 = filterLeadingZeroes(version2);
        
        return compare(v1, v2);
    }
    
    private String filterLeadingZeroes(String s) {
        String[] data = s.split("\\.");
        if (data.length == 1)
            return s;
        
        for (int i = 0; i < data.length; ++i) {
            int j = 0;
            
            while (j < data[i].length() && data[i].charAt(j) == '0')
                ++j;
                
            data[i] = data[i].substring(j, data[i].length());
        }
        
        StringBuilder builder = new StringBuilder();
        builder.append(validateValue(data[0]));
        for (int i = 1; i < data.length; ++i) {
            String result = validateValue(data[i]);
            builder.append(".");
            builder.append(result);
        }
        
        return builder.toString();
    }
    
    private int compare(String v1, String v2) {
        String[] data1 = v1.split("\\.");
        String[] data2 = v2.split("\\.");
        // System.out.printf("%s\n", Arrays.toString(data1));
        // System.out.printf("%s\n", Arrays.toString(data2));
        
        int i, j;
        for (i = 0, j = 0; i < data1.length || j < data2.length; ++j, ++i) {
            int val1 = ((i >= data1.length) ? 0 : Integer.parseInt(data1[i]));
            int val2 = ((j >= data2.length) ? 0 : Integer.parseInt(data2[j]));
            
            if (val1 < val2)
                return -1;
            if (val1 > val2)
                return 1;
        }
        
        return 0;
    }
    
    private String validateValue(String value) {
        return ((value.equals("")) ? "0" : value);
    }
}