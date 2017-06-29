/**
 * Copyright 2013 Netherlands eScience Center
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package nl.esciencecenter.xenon.adaptors.file.sftp;

import java.util.HashMap;

import nl.esciencecenter.xenon.Xenon;
import nl.esciencecenter.xenon.adaptors.job.ssh.SSHJobTestConfig;
import nl.esciencecenter.xenon.credentials.DefaultCredential;
import nl.esciencecenter.xenon.files.FileSystem;
import nl.esciencecenter.xenon.files.Files;

/**
 * 
 */
public class SftpTunnelTest {

    @org.junit.Test
    public void test_sshViaTunnel() throws Exception {
        SSHJobTestConfig config = new SSHJobTestConfig(null);
        
        String gateway = config.getPropertyOrFail("test.ssh.gateway");
        String location = config.getPropertyOrFail("test.ssh.location");
        String username = config.getPropertyOrFail("test.ssh.user");
        
        if (username != null) {
            location = username + "@" + location;
        }
        
        Files files = Xenon.files();

        HashMap<String, String> properties = new HashMap<>(3);
        properties.put("xenon.adaptors.ssh.gateway", gateway);
        properties.put("xenon.adaptors.ssh.strictHostKeyChecking", "false");
        
//        System.out.println("gateway = " + gateway);
//        System.out.println("location = " + location);
//        System.out.println("credential = " + credentials.getDefaultCredential("sftp"));
//        
        // Will thrown an exception if the tunnel fails ?
        FileSystem filesystem = files.newFileSystem("ssh", location, new DefaultCredential(), properties);

        files.close(filesystem);
        Xenon.endAll();
    }

    public static void main(String [] args) throws Exception { 
        new SftpTunnelTest().test_sshViaTunnel();
    }
    
    
}
