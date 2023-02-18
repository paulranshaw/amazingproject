<!-- PROJECT SHIELDS -->
[![MIT License][license-shield]][license-url]

<!-- ABOUT -->
## About

**AMazingProject** is Group 13's custom [Minecraft](https://minecraft.net) modification developed with [Forge](https://github.com/MinecraftForge/MinecraftForge), a modding API for Minecraft written in Java.

### Built With
* [Java](https://dev.java/)
* [Forge](https://github.com/MinecraftForge/MinecraftForge)

<!-- TABLE OF CONTENTS -->
#### Table of Contents
* [About](#about)
  * [Built With](#built-with)
* [Getting Started for Contributors](#getting-started-for-contributors)
* [License](#license)

## Getting Started for Contributors

Required accounts and software:

1. Download and install [Git (Windows)](https://git-scm.com/download/win) [Git (MacOS)](https://git-scm.com/download/mac)
2. Create a [Oracle account](https://profile.oracle.com/myprofile/account/create-account.jspx)
3. Download and install [Java SE Runtime Environment 8u202](https://www.oracle.com/uk/java/technologies/javase/javase8-archive-downloads.html)
   * Type `Edit the system environment variables into the Windows search bar`
   * Select `Environment Variables`
   * Select `JAVA_HOME` under `Variable` and set to `C:\ProgramFiles\Java\jre1.8.0_202` if you installed the above in the default location.
4. Download and install [Java SE 17 Development Kit 17.0.6](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
5. Apply for an [education license for IntelliJ IDEA](https://www.jetbrains.com/shop/eform/students)
6. Download and install [IntelliJ IDEA](https://www.jetbrains.com/idea/) and then sign in to activate the product as Ultimate edition.
7. Create a [GitHub account](https://github.com/signup) then send Paul your username to be added as a collaborator.

Cloning the repository and setting up your development environment:

1. Launch IntelliJ IDEA, by default you should see the Projects window. (If you don't see this, select File > New > Project from Version Control and continue)
   * Select `Get from VCS` in the top right of the window (Skip this if you didn't see the Projects window)
   * Select `GitHub`
   * You should now be prompted to login with your GitHub account
   * Once logged in, select `amazingproject`
   * Select `Clone`
     * If `Load Project` appears in lower center to right area of the window with `Gradle` mentioned, select this.

2. Now you have successfully cloned the project, you need to 'checkout' the correct branch to make changes to code.
   * Select `Terminal` in IntelliJ IDEA, this is the 4th tab along the bottom of the IDE window
   * Enter `git checkout develop`
   * Git should state 'Your branch is up to date with 'origin/develop''

3. To fully setup our environment, we need to complete one more step to make sure Gradle is working and can build from our cloned repository.
   * Enter `./gradlew genIntelliJRuns` into the Terminal, this should state 'BUILD SUCCESSFUL in Xs'
   * Re-run this command, you should notice that there is a different number of 'X actionable tasks'. Once you have re-ran the command, the development environment is ready.

<!-- LICENSE -->
## License
Distributed under the MIT License. See `LICENSE` for more information. This project uses Minecraft Forge, Forge Mod Loader. See `LICENSE.txt or LICENSE-X.. .txt` for more information.

<!-- MARKDOWN LINKS & IMAGES -->
[license-shield]: https://img.shields.io/badge/license-MIT-blue.svg
[license-url]: https://choosealicense.com/licenses/mit