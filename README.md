# RuntimePermission
Run-time permission : If an app needs to use resources or information outside of its own sandbox, the app has to request the appropriate permission.

#Gradle
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  
  dependencies {
	        implementation 'com.github.manoj140220:RuntimePermission:1.0.0'
	}
  
  # Usage
  
  new RuntimePermission({Current Class Object}, String[] , ActvityContext);
  
  Impliment : {PermissionNotify} in the class where you are calling "RuntimePermission".
  
  Response will sent to,
  
    @Override
    public void notifyPermissionGrant() {
        
    }
    
    @Override
    public void notifyPermissionDeny() {
         
    }
