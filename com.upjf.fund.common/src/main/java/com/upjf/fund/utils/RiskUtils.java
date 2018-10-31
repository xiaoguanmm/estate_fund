package com.upjf.fund.utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.CodeSource;
import java.security.ProtectionDomain;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Map;

/**
 * 常用工具类
 * 
 * @author wufujing
 * 
 */
public class RiskUtils {
    private static String HanDigiStr[] = new String[] { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖" };

    private static String HanDiviStr[] = new String[] { "", "拾", "佰", "仟", "万", "拾", "佰", "仟", "亿", "拾", "佰", "仟", "万", "拾", "佰", "仟", "亿", "拾", "佰", "仟", "万", "拾", "佰", "仟" };

    /**
     * 将传入的身份证号码进行校验，并返回一个对应的18位身份证
     * 
     * @param personIDCode
     *            身份证号码
     * @return String 十八位身份证号码
     * @throws 无效的身份证号
     */
    public static String getFixedPersonIDCode(String personIDCode) throws Exception {
        if (personIDCode == null)
            throw new Exception("输入的身份证号无效，请检查");

        if (personIDCode.length() == 18) {
            if (isIdentity(personIDCode))
                return personIDCode;
            else
                throw new Exception("输入的身份证号无效，请检查");
        } else if (personIDCode.length() == 15)
            return fixPersonIDCodeWithCheck(personIDCode);
        else
            throw new Exception("输入的身份证号无效，请检查");
    }

    /**
     * 修补15位居民身份证号码为18位，并校验15位身份证有效性
     * 
     * @param personIDCode
     *            十五位身份证号码
     * @return String 十八位身份证号码
     * @throws 无效的身份证号
     */
    public static String fixPersonIDCodeWithCheck(String personIDCode) throws Exception {
        if (personIDCode == null || personIDCode.trim().length() != 15)
            throw new Exception("输入的身份证号不足15位，请检查");

        if (!isIdentity(personIDCode))
            throw new Exception("输入的身份证号无效，请检查");

        return fixPersonIDCodeWithoutCheck(personIDCode);
    }

    /**
     * 修补15位居民身份证号码为18位，不校验身份证有效性
     * 
     * @param personIDCode
     *            十五位身份证号码
     * @return 十八位身份证号码
     * @throws 身份证号参数不是15位
     */
    public static String fixPersonIDCodeWithoutCheck(String personIDCode) throws Exception {
        if (personIDCode == null || personIDCode.trim().length() != 15)
            throw new Exception("输入的身份证号不足15位，请检查");

        String id17 = personIDCode.substring(0, 6) + "19" + personIDCode.substring(6, 15); // 15位身份证补'19'

        char[] code = { '1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2' }; // 11个校验码字符
        int[] factor = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1 }; // 18个加权因子
        int[] idcd = new int[18];
        int sum; // 根据公式 ∑(ai×Wi) 计算
        int remainder; // 第18位校验码
        for (int i = 0; i < 17; i++) {
            idcd[i] = Integer.parseInt(id17.substring(i, i + 1));
        }
        sum = 0;
        for (int i = 0; i < 17; i++) {
            sum = sum + idcd[i] * factor[i];
        }
        remainder = sum % 11;
        String lastCheckBit = String.valueOf(code[remainder]);
        return id17 + lastCheckBit;
    }

    /**
     * 判断是否是有效的18位或15位居民身份证号码
     * 
     * @param identity
     *            18位或15位居民身份证号码
     * @return 是否为有效的身份证号码
     */
    public static boolean isIdentity(String identity) {
        if (identity == null)
            return false;
        if (identity.length() == 18 || identity.length() == 15) {
            String id15 = null;
            if (identity.length() == 18)
                id15 = identity.substring(0, 6) + identity.substring(8, 17);
            else
                id15 = identity;
            try {
                Long.parseLong(id15); // 校验是否为数字字符串

                String birthday = "19" + id15.substring(6, 12);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                sdf.parse(birthday); // 校验出生日期
                if (identity.length() == 18 && !fixPersonIDCodeWithoutCheck(id15).equals(identity))
                    return false; // 校验18位身份证
            } catch (Exception e) {
                return false;
            }
            return true;
        } else
            return false;
    }

    /**
     * 从身份证号中获取出生日期，身份证号可以为15位或18位
     * 
     * @param identity
     *            身份证号
     * @return 出生日期
     * @throws 身份证号出生日期段有误
     */
    public static Timestamp getBirthdayFromPersonIDCode(String identity) throws Exception {
        String id = getFixedPersonIDCode(identity);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        try {
            Timestamp birthday = new Timestamp(sdf.parse(id.substring(6, 14)).getTime());
            return birthday;
        } catch (ParseException e) {
            throw new Exception("不是有效的身份证号，请检查");
        }
    }

    /**
     * 从身份证号获取性别
     * 
     * @param identity
     *            身份证号
     * @return 性别代码
     * @throws Exception
     *             无效的身份证号码
     */
    public static String getGenderFromPersonIDCode(String identity) throws Exception {
        String id = getFixedPersonIDCode(identity);
        char sex = id.charAt(16);
        return sex % 2 == 0 ? "2" : "1";
    }

    /**
     * 将货币转换为大写形式(类内部调用)
     * 
     * @param val
     * @return String
     */
    private static String PositiveIntegerToHanStr(String NumStr) {
        // 输入字符串必须正整数，只允许前导空格(必须右对齐)，不宜有前导零
        String RMBStr = "";
        boolean lastzero = false;
        boolean hasvalue = false; // 亿、万进位前有数值标记
        int len, n;
        len = NumStr.length();
        if (len > 15)
            return "数值过大!";
        for (int i = len - 1; i >= 0; i--) {
            if (NumStr.charAt(len - i - 1) == ' ')
                continue;
            n = NumStr.charAt(len - i - 1) - '0';
            if (n < 0 || n > 9)
                return "输入含非数字字符!";

            if (n != 0) {
                if (lastzero)
                    RMBStr += HanDigiStr[0]; // 若干零后若跟非零值，只显示一个零
                // 除了亿万前的零不带到后面
                // if( !( n==1 && (i%4)==1 && (lastzero || i==len-1) ) )
                // 如十进位前有零也不发壹音用此行
                if (!(n == 1 && (i % 4) == 1 && i == len - 1)) // 十进位处于第一位不发壹音
                    RMBStr += HanDigiStr[n];
                RMBStr += HanDiviStr[i]; // 非零值后加进位，个位为空
                hasvalue = true; // 置万进位前有值标记

            } else {
                if ((i % 8) == 0 || ((i % 8) == 4 && hasvalue)) // 亿万之间必须有非零值方显示万
                    RMBStr += HanDiviStr[i]; // “亿”或“万”
            }
            if (i % 8 == 0)
                hasvalue = false; // 万进位前有值标记逢亿复位
            lastzero = (n == 0) && (i % 4 != 0);
        }

        if (RMBStr.length() == 0)
            return HanDigiStr[0]; // 输入空字符或"0"，返回"零"
        return RMBStr;
    }

    /**
     * 将货币转换为大写形式
     * 
     * @param val
     *            传入的数据
     * @return String 返回的人民币大写形式字符串
     */
    public static String numToRMBStr(double val) {
        String SignStr = "";
        String TailStr = "";
        long fraction, integer;
        int jiao, fen;

        if (val < 0) {
            val = -val;
            SignStr = "负";
        }
        if (val > 99999999999999.999 || val < -99999999999999.999)
            return "数值位数过大!";
        // 四舍五入到分
        long temp = Math.round(val * 100);
        integer = temp / 100;
        fraction = temp % 100;
        jiao = (int) fraction / 10;
        fen = (int) fraction % 10;
        if (jiao == 0 && fen == 0) {
            TailStr = "整";
        } else {
            TailStr = HanDigiStr[jiao];
            if (jiao != 0)
                TailStr += "角";
            // 零元后不写零几分
            if (integer == 0 && jiao == 0)
                TailStr = "";
            if (fen != 0)
                TailStr += HanDigiStr[fen] + "分";
        }
        // 下一行可用于非正规金融场合，0.03只显示“叁分”而不是“零元叁分”
        return SignStr + PositiveIntegerToHanStr(String.valueOf(integer)) + "元" + TailStr;
    }

    /**
     * 判断对象是否Empty(null或元素为0)<br>
     * 实用于对如下对象做判断:String Collection及其子类 Map及其子类
     * 
     * @param pObj
     *            待检查对象
     * @return boolean 返回的布尔值
     */
    public static boolean isEmpty(Object pObj) {
        if (pObj == null)
            return true;
        if (pObj == "")
            return true;
        if (pObj instanceof String) {
            if (((String) pObj).length() == 0) {
                return true;
            }
        } else if (pObj instanceof Collection) {
            if (((Collection) pObj).size() == 0) {
                return true;
            }
        } else if (pObj instanceof Map) {
            if (((Map) pObj).size() == 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断对象是否为NotEmpty(!null或元素>0)<br>
     * 实用于对如下对象做判断:String Collection及其子类 Map及其子类
     * 
     * @param pObj
     *            待检查对象
     * @return boolean 返回的布尔值
     */
    public static boolean isNotEmpty(Object pObj) {
        if (pObj == null)
            return false;
        if (pObj == "")
            return false;
        if (pObj instanceof String) {
            if (((String) pObj).length() == 0) {
                return false;
            }
        } else if (pObj instanceof Collection) {
            if (((Collection) pObj).size() == 0) {
                return false;
            }
        } else if (pObj instanceof Map) {
            if (((Map) pObj).size() == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 合并字符串数组
     * 
     * @param a
     *            字符串数组0
     * @param b
     *            字符串数组1
     * @return 返回合并后的字符串数组
     */
    public static String[] mergeStringArray(String[] a, String[] b) {
        if (a.length == 0 || isEmpty(a))
            return b;
        if (b.length == 0 || isEmpty(b))
            return a;
        String[] c = new String[a.length + b.length];
        for (int m = 0; m < a.length; m++) {
            c[m] = a[m];
        }
        for (int i = 0; i < b.length; i++) {
            c[a.length + i] = b[i];
        }
        return c;
    }

    /**
     * 获取class文件所在绝对路径
     * 
     * @param cls
     * @return
     * @throws IOException
     */
    public static String getPathFromClass(Class cls) {
        String path = null;
        if (cls == null) {
            throw new NullPointerException();
        }
        URL url = getClassLocationURL(cls);
        if (url != null) {
            path = url.getPath();
            if ("jar".equalsIgnoreCase(url.getProtocol())) {
                try {
                    path = new URL(path).getPath();
                } catch (MalformedURLException e) {
                }
                int location = path.indexOf("!/");
                if (location != -1) {
                    path = path.substring(0, location);
                }
            }
            File file = new File(path);
            try {
                path = file.getCanonicalPath();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return path;
    }

    /**
     * 获取类的class文件位置的URL
     * 
     * @param cls
     * @return
     */
    private static URL getClassLocationURL(final Class cls) {
        if (cls == null)
            throw new IllegalArgumentException("null input: cls");
        URL result = null;
        final String clsAsResource = cls.getName().replace('.', '/').concat(".class");
        final ProtectionDomain pd = cls.getProtectionDomain();
        if (pd != null) {
            final CodeSource cs = pd.getCodeSource();
            if (cs != null)
                result = cs.getLocation();
            if (result != null) {
                if ("file".equals(result.getProtocol())) {
                    try {
                        if (result.toExternalForm().endsWith(".jar") || result.toExternalForm().endsWith(".zip"))
                            result = new URL("jar:".concat(result.toExternalForm()).concat("!/").concat(clsAsResource));
                        else if (new File(result.getFile()).isDirectory())
                            result = new URL(result, clsAsResource);
                    } catch (MalformedURLException ignore) {
                    }
                }
            }
        }
        if (result == null) {
            final ClassLoader clsLoader = cls.getClassLoader();
            result = clsLoader != null ? clsLoader.getResource(clsAsResource) : ClassLoader.getSystemResource(clsAsResource);
        }
        return result;
    }

    /**
     * 将字符串写入指定文件 (当指定的父路径中文件夹不存在时，会最大限度去创建，以保证保存成功！)
     * 
     * @param res
     *            原字符串
     * @param filePath
     *            文件路径
     * @return 成功标记
     */
    public static boolean writeString2File(String res, String filePath) {
        boolean flag = true;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        try {
            File distFile = new File(filePath);
            if (!distFile.getParentFile().exists())
                distFile.getParentFile().mkdirs();
            bufferedReader = new BufferedReader(new StringReader(res));
            bufferedWriter = new BufferedWriter(new FileWriter(distFile));
            char buf[] = new char[1024];
            int len;
            while ((len = bufferedReader.read(buf)) != -1) {
                bufferedWriter.write(buf, 0, len);
            }
            bufferedWriter.flush();
            bufferedReader.close();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
            flag = false;
            return flag;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return flag;
    }

    /**
     * 文本文件转换为指定编码的字符串
     * 
     * @param file
     *            文本文件
     * @param encoding
     *            编码类型
     * @return 转换后的字符串
     * @throws IOException
     */
    public static String readStringFromFile(File file, String encoding) {
        InputStreamReader reader = null;
        StringWriter writer = new StringWriter();
        try {
            if (encoding == null || "".equals(encoding.trim())) {
                reader = new InputStreamReader(new FileInputStream(file), encoding);
            } else {
                reader = new InputStreamReader(new FileInputStream(file));
            }
            char[] buffer = new char[1024];
            int n = 0;
            while (-1 != (n = reader.read(buffer))) {
                writer.write(buffer, 0, n);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (reader != null)
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        if (writer != null)
            return writer.toString();
        else
            return null;
    }
    
	 //将字符串转移为ASCII码
	 public static String getCnASCII(String cnStr)
	 {
		 StringBuffer   strBuf   =   new   StringBuffer();
		 byte[]   bGBK   =   cnStr.getBytes();
	     for(int   i=0;i <bGBK.length;i++){
	             strBuf.append(Integer.toHexString(bGBK[i]&0xff));
	     }
	     return strBuf.toString();
	 }
	 
	/**
	 * 流转字符串方法
	 * 
	 * @param inputStream
	 * @return
	 */
	public static String convertStreamToString(InputStream inputStream) {
		if (inputStream == null)
			return "";
		StringBuilder sb = new StringBuilder();
		try {
			BufferedInputStream bufferedInputStream = new BufferedInputStream(
					inputStream);
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					bufferedInputStream));
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			bufferedInputStream.close();
			reader.close();
			inputStream.close();
		} catch (IOException e) {
			return "";
		} catch (Exception ex) {
			return "";
		}
		return sb.toString();
	}
}
