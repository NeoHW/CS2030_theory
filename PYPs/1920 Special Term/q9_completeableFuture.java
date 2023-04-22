import java.util.concurrent.CompletableFuture;

public CompletableFuture<LoginStatus> userLogin(String username, String password) {
  if (this.loggedIn) {
    // return completedFuture as it is alr done
    return CompletableFuture.completedFuture<LoginStatus>(new LoginStatus(true,"Already logged in!"));
  }

  // use cf to check if username and password exist at same time
  CompletableFuture<Boolean> cfu = CompleteableFuture.supplyAsync(() -> usernameExists(username));
  CompletableFuture<Boolean> cfp = CompleteableFuture.supplyAsync(() -> isValidPassword(password));
  
  // using thenCombine
  return cfu.thenCombineAsync(cfp, (u,p) -> {
    if (u && p) {
      return CompletableFuture.supplyAsync(() -> doLogin(username, password))
        .thenApplyAsync(loginSucess -> { // mapping the boolean to a login status , still wrapped in CF
          if(loginSucess) {
            return new LoginStatus(true,"Login by password.");
          } else {
            return new LoginStatus(false,"No Server Response");
          }
        });
    } else {
      return CompletableFuture.completedFuture(new LoginStatus(false,"Incorrect Username/Password"));
    }
  });  
}

//original method in qn
public LoginStatus userLogin(String username, String password) {
  if(this.loggedIn) {
    return new LoginStatus(true,"Already logged in!");
  } else {
    Boolean u = usernameExists(username);
    Boolean p = isValidPassword(password);
    if (u && p) {
      if(doLogin(username, password)) {
        return new LoginStatus(true,"Login by password.");
      } else {
        return new LoginStatus(false,"No Server Response");
      }
    } else {
      return new LoginStatus(false,"Incorrect Username/Password");
    }
  }
}