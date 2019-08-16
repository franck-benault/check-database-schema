package net.franckbenault.checkdb;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

import net.franckbenault.checkdb.input.DatabaseConnection;
import net.franckbenault.checkdb.input.DatabaseType;
import net.franckbenault.checkdb.input.Rule;
import net.franckbenault.checkdb.output.CheckOutput;

public class Main {

	@Parameter(names = { "--databaseType", "-dt" })
	private String databaseType;

	@Parameter(names = { "--databaseUrl", "-url" })
	private String url;

	@Parameter(names = { "--databaseUser", "-user" })
	private String user;

	@Parameter(names = { "--databasePassword", "-pwd" })
	private String password;

	@Parameter(names = { "--fileNameRules", "-fr" })
	private String fileNameRules;

	public static void main(String... argv) {

		Main main = new Main();
		JCommander.newBuilder().addObject(main).build().parse(argv);
		main.run();
	}

	private void run() {

		DatabaseType dbType = DatabaseType.valueOf(databaseType);

		DatabaseConnection dbConnection = new DatabaseConnection(dbType, user, user, password);
		Set<Rule> rules = new HashSet<>();

		try (BufferedReader br = new BufferedReader(new FileReader(fileNameRules))) {
			String line = br.readLine();
			while (line != null) {
				line = br.readLine();
				Rule rule = new Rule(line);
				rules.add(rule);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		CheckOutput output = Check.check(dbConnection, rules);
		System.out.println(output);

	}

}
