package m0417;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SortUtil {
	/**
	 * ���ַ����е�����ת��Ϊ ָ�� ���ȵ����ִ�
	 * 
	 * @param content
	 * @return ����ת������ַ���
	 */
	public static String changeIntToSpecifyLength(String content) {

		List<String> intMatchList = new ArrayList<String>();// ƥ������ֵ�List
		List<String> unIntSubList = new ArrayList<String>();// ��ȡ�ķ����ֵ�List
		List<String> intChangeList = new ArrayList<String>();// ƥ�������ת�����List

		// ���ʽ�Ĺ��ܣ���֤����Ϊ���֣�������С����
		String pattern = "[0-9]+([0-9]+)?";
		// ��()���÷��ܽ᣺��()�еı��ʽ��Ϊһ��������д�������������������ṹ�ſ��ԡ�
		// (.[0-9]+)? ����ʾ()�е��������һ�λ�һ��Ҳ������
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(content);

		int unIntSubStartIndex = 0; // �����ӽ�ȡ�Ŀ�ʼindex��Ĭ��Ϊ0

		while (m.find()) {
			intMatchList.add(m.group());

			int unIntSubEndIndex = 0; // ���η����ֽ�ȡ�Ľ���index��ֵ������ƥ��Ŀ�ʼ����Ĭ��Ϊ0
			if (m.start() != 0) {
				unIntSubEndIndex = m.start();
			}

			// ��ȡ ������ �ַ���
			String sub = content
					.substring(unIntSubStartIndex, unIntSubEndIndex);
			unIntSubList.add(sub);

			// ��������ƥ��Ľ���index�� ��һ�� �����ֽ�ȡ�Ŀ�ʼ
			unIntSubStartIndex = m.end();
		}
		// ��ȡ ��� �����ֵ� �ַ���
		String sub1 = content
				.substring(unIntSubStartIndex, content.length());
		unIntSubList.add(sub1);

		// ��ƥ�������ת��Ϊ ָ�����ȵ�����
		if (intMatchList != null && !intMatchList.isEmpty()) {
			for (String string : intMatchList) {
				String changeStr = changeIntLength(string, 9);
				intChangeList.add(changeStr);
			}
		}

		// ������ƴ��
		String temp = "";
		if (unIntSubList != null && intMatchList != null
				&& unIntSubList.size() == intChangeList.size() && intMatchList.size() > 0) {
			for (int i = 0; i < intMatchList.size(); i++) {
				// ������ + ת���������
				temp += unIntSubList.get(i) + intChangeList.get(i);
			}

			content = temp;
		}else if(unIntSubList != null && intMatchList != null
				&& unIntSubList.size() > intChangeList.size() && intMatchList.size() > 0){
			for (int i = 0; i < intMatchList.size(); i++) {
				// ������ + ת���������
				temp += unIntSubList.get(i) + intChangeList.get(i);
			}
			temp += unIntSubList.get(unIntSubList.size()-1);
			
			content = temp;
		}else{
			return content;
		}

		return content;

	}

	/**
	 * ������ת��Ϊָ�����ȵ�����
	 * 
	 * @param value
	 * @param retLength
	 * @return ����ָ�����ȵ����� �ַ���
	 */
	private static String changeIntLength(String value, int retLength) {
		String ret = value;
		if (value != null && !value.equals("")) {
			int intValue = Integer.valueOf(value);

			char[] cc = new char[retLength];
			int i = 0;
			for (i = 0; i < retLength; i++) {
				cc[i] = '0';
			}

			DecimalFormat df = new DecimalFormat(new String(cc));
			ret = df.format(intValue);
		}

		return ret;
	}
}