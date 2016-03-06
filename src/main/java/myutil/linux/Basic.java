package myutil.linux;

import java.io.IOException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

/**
 * 连接linux的工具
 * @author Europe
 *
 */
public class Basic
{
    public static void main(String[] args)
    {
        String hostname = "115.28.140.51";
        String username = "root";
        String password = "9u3sNqb&nS$e";

        try
        {
           

            Connection conn = new Connection(hostname);

           

            conn.connect();

           

            boolean isAuthenticated = conn.authenticateWithPassword(username, password);
            System.out.println(isAuthenticated);
            
            
            if (isAuthenticated == false)
                throw new IOException("Authentication failed.");

           

            Session sess = conn.openSession();

            sess.execCommand("uname -a && date && uptime && who");

            System.out.println("Here is some information about the remote host:");

           

            InputStream stdout = new StreamGobbler(sess.getStdout());

            BufferedReader br = new BufferedReader(new InputStreamReader(stdout));

            while (true)
            {
                String line = br.readLine();
                if (line == null)
                    break;
                System.out.println(line);
            }

           

            System.out.println("ExitCode: " + sess.getExitStatus());

           

            sess.close();

           

            conn.close();

        }
        catch (IOException e)
        {
            e.printStackTrace(System.err);
            System.exit(2);
        }
    }
}