import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class big_calculator {
    String a;
    String b;
    public static void main(String[] args) throws IOException { 
        big_calculator data = new big_calculator();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Nhap so dau tien: ");
        String a = br.readLine();        
        System.out.print("Nhap so thu hai:");
        String b = br.readLine();
        data.a = a; data.b = b;
        chuanHoaDoDai(data);
        
        String tong, hieu;
        tong = phepCong(data);
        System.out.println("Ket qua cua phep cong:");
        System.out.println(tong);
        System.out.println("-----------------------------------------------------");
        hieu = phepTru(data);
        System.out.println("Ket qua cua phep tru:");
        System.out.println(hieu);
    }

    public static void chuanHoaDoDai(big_calculator data) throws IOException { 
        String a = data.a;
        String b = data.b;
        // Tinh toan so luong chu so truoc va sau dau phay cua so a
        int idx_coma=a.indexOf(','), bcoma, acoma, bcomb, acomb;
        if (idx_coma == -1){
            bcoma = a.length();
            acoma = 0;
        }
        else{
            bcoma = idx_coma;
            acoma = a.length() - idx_coma -1;
        }

        // Tinh toan so luong chu so truoc va sau dau phay cua so a
        int idx_comb=b.indexOf(',');
        if (idx_comb == -1){
            bcomb = b.length();
            acomb = 0;
        }
        else{
            bcomb = idx_comb;
            acomb = b.length() - idx_comb -1;
        }
        
        // Them so 0 vao sau so co so luong chu so sau dau phay nho hon
        if (acoma < acomb){
            if (acoma == 0){
                a = a + ',';
            }
            while (acoma < acomb){
                a = a + '0';
                acoma++;
            }
        }
        else {
            if (acomb == 0){
                b = b + ',';
            }
            while (acoma > acomb){
                b = b + '0';
                acomb++;
            }
        }
        
        // Them so 0 vao truoc so co so luong chu so truoc dau phay nho hon
        if (bcoma < bcomb){
            while (bcoma < bcomb){
                a = '0' + a;
                bcoma++;
            }
        }
        else {
            while (bcoma > bcomb){
                b = '0' + b;
                bcomb++;
            }
        }
        data.a = a; data.b = b;
    }
    public static String phepCong(big_calculator data) throws IOException { 
        String a = data.a;
        String b = data.b;
        int flag = 0;
        String result = "";
        for (int i=a.length()-1; i>=0; i--){
            if (a.charAt(i) != ','){
                int ai=Integer.parseInt(String.valueOf(a.charAt(i)));
                int bi=Integer.parseInt(String.valueOf(b.charAt(i)));
                int temp = ai + bi + flag;
                if (temp > 9){
                    if (i == 0){
                        result = temp + result;
                    }
                    else{
                        result = temp%10 + result;
                    }
                    flag = 1;
                } 
                else{
                    flag = 0;
                    result = temp + result;
                }
            }
            else{
                result = ',' + result;
            }
        }        
        
        // Loai bo nhung so 0 cuoi cung
        // Loai bo dau "," neu la so nguyen 
        int com = result.indexOf(',');
        if (com != -1){
            while (((result.charAt(result.length()-1) == '0') || (result.charAt(result.length()-1) == ',')) && (com<result.length())){
                result = result.substring(0, result.length() - 1);
            }
        }
        return result;
    }

    public static int soSanh(big_calculator data) throws IOException { 
        String a = data.a;
        String b = data.b;

        for (int i=0; i<a.length(); i++){
            if (a.charAt(i) > b.charAt(i)){
                return 1; 
            }
            else if (a.charAt(i) < b.charAt(i)){
                return -1;
            }
        }
        return 0;
    }
    public static String phepTru(big_calculator data) throws IOException { 
        String a = data.a;
        String b = data.b;
        
        boolean neg = false; 
        int sosanh = soSanh(data);
        if (sosanh==0){
            return "0";
        }
        else if (sosanh==-1){
            String tmp = a;
            a = b;
            b = tmp;
            neg = true; 
        }

        int flag = 0;
        String result = "";
        for (int i=a.length()-1; i>=0; i--){
            if (a.charAt(i) != ','){
                int ai=Integer.parseInt(String.valueOf(a.charAt(i)));
                int bi=Integer.parseInt(String.valueOf(b.charAt(i)));
                int temp = ai - bi - flag;
                if (temp < 0){
                    flag = 1;
                    temp = temp + 10;
                    result = temp + result;
                }
                else{
                    result = temp + result;
                    flag =  0;
                }
            }
            else{
                result = ',' + result;
            }
        }        
        
        // Loai bo nhung so 0 cuoi cung
        // Loai bo dau "," neu la so nguyen 
        int com = a.indexOf(',');
        if (com != -1){
            while (((result.charAt(result.length()-1) == '0') || (result.charAt(result.length()-1) == ',')) && (com<result.length())){
                result = result.substring(0, result.length() - 1);
            }
        }
        result = result.replaceAll("^[0]*", "");
        if (result.charAt(0) == ',') result = '0' + result; 
        if (neg) result = '-' + result;
        return result;
    }
        
}