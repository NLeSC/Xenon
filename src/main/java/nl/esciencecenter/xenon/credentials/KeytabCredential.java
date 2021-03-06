/*
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
package nl.esciencecenter.xenon.credentials;

import java.util.Objects;

public class KeytabCredential implements UserCredential {

    private final String username;
    private final String keytabFile;

    public KeytabCredential(String username, String keytabFile) {
        this.username = username;
        this.keytabFile = keytabFile;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public String getKeytabFile() {
        return keytabFile;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;

        KeytabCredential that = (KeytabCredential) obj;
        return Objects.equals(username, that.username) && Objects.equals(keytabFile, that.keytabFile);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, keytabFile);
    }
}
