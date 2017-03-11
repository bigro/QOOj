import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.LinkedList;
import java.util.List;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.junit.Test;

import qooj.jooq.generate.tables.Test1;
import qooj.jooq.generate.tables.records.Test1Record;

public class AutoGenerateTest {

	@Test
	public void test() throws Exception {
		String userName = "sa";
		String password = "";
		String url = "jdbc:h2:file:~/.QOOj/h2/db;MODE=MySQL;";

		try (Connection conn = DriverManager.getConnection(url, userName, password)) {
			DSLContext create = DSL.using(conn, SQLDialect.MYSQL);
			List<Test1Record> withgenarate = create
				.select().from(Test1.TEST1)
				.where("code = 1")
				.fetch().into(Test1Record.class);
			System.out.println(withgenarate);
			assertThat(withgenarate).hasSize(1).extracting("name").contains("AAA");

			LinkedList<Test1Record> linkedList = new LinkedList<>();
			List<Record> withoutgenarate = create
				.select().from("qooj.test1")
				.where("code = 1")
				.fetch();
			for (Record record : withoutgenarate) {
				Test1Record test1Record = new Test1Record();
				
				// これを項目数分書く
				test1Record.setName(record.getValue("NAME", String.class));
				linkedList.add(test1Record);
			}
			assertThat(linkedList).hasSize(1).extracting("name").contains("AAA");
}
	}

}
