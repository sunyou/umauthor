package com.ai.umauthor;

import com.ai.common.dynasql.Strings;
import com.ai.common.dynasql.TableColsGenerator;
import com.google.common.io.Files;
import org.junit.Test;
import org.mybatis.generator.api.ShellRunner;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * MybatisGen
 *
 * @author mysh
 * @since 2016/1/18
 */
public class MybatisGen {
	@Test
	public void genMybatisBean() throws IOException, ClassNotFoundException {
		//String file = "F:\\myeclipse2014workspace\\umauthor\\src\\main\\resources\\mybatis-gen.xml";
		String path = MybatisGen.class.getClassLoader().getResource("mybatis-gen.xml").getPath();
		String[] paths = path.split("target");
		String file = paths[0] + "src/main/resources/mybatis-gen.xml";
		ShellRunner.main(new String[]{"-configfile", file, "-overwrite"});
	}

	@Test
	public void genTableCols() throws IOException, ClassNotFoundException {
		TableColsGenerator.genSingle("com.ai.umauthor.server.model",
						"F:\\myeclipse2014workspace\\umauthor\\src\\main\\java",
						"com.ai.umauthor.server.model", "TableCols");
	}

	@Test
	public void genCols2Bean() throws IOException, ClassNotFoundException {
		TableColsGenerator.gen2Bean("D:\\alPrj\\umauthor\\src\\main\\java",
						"com.ai.umauthor.server.model");
	}

	@Test
	public void genTableConf() throws Exception {
		String temp = "<table tableName=\"%s\" schema=\"\">\n" +
						"\t\t\t<property name=\"ignoreQualifiersAtRuntime\" value=\"true\"/>\n" +
						"\t\t</table>";
		List<String> lines = Files.readLines(new File("c:/a.txt"), StandardCharsets.UTF_8);
		for (String line : lines) {
			if (Strings.isNotBlank(line)) {
				System.out.println(String.format(temp, line));
			}
		}
	}
}
