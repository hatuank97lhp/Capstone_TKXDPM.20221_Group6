package infrastructure.repository.common;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import org.apache.ibatis.jdbc.ScriptRunner;

public class DbMigration {

    public static void runScript(Connection conn) throws IOException, SQLException {
        var sr = new ScriptRunner(conn);
        sr.runScript(new BufferedReader(new FileReader("db/initdb.sql")));
    }
}
