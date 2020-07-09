# RuntimePermission
Run-time permission : If an app needs to use resources or information outside of its own sandbox, the app has to request the appropriate permission.

# Gradle

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  
	dependencies {
		implementation 'com.github.manoj140220:RuntimePermission:1.0.3'
	}
  
  # Usage
  
	  new RuntimePermission({Current Class Object}, String[] , {ActvityContext});
	  
	  String[] : permission array.
	  example  : String[] permissionArray = {Manifest.permission.CAMERA, Manifest.permission.BODY_SENSORS,...} 
  
  Impliment : {PermissionNotify} in the class where you are calling "RuntimePermission".
  
  Response will sent to,
  
    @Override
    public void notifyPermissionGrant() {
        
    }
    
    @Override
    public void notifyPermissionDeny() {
         
    }

# Licence

	   Copyright 2020 Manoj DB

	   Licensed under the Apache License, Version 2.0 (the "License");
	   you may not use this file except in compliance with the License.
	   You may obtain a copy of the License at

	       http://www.apache.org/licenses/LICENSE-2.0

	   Unless required by applicable law or agreed to in writing, software
	   distributed under the License is distributed on an "AS IS" BASIS,
	   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	   See the License for the specific language governing permissions and
	   limitations under the License.
