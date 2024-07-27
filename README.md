# App Description
Flag guesser android app with saving the last one game result. Made in 3 hours 04 mins. Tesk task for Sigma Software.
Stack: Kotlin, Compose, Coroutines, MVVM, Clean Arch, Single Activity, Dependecy Injection (Hilt), Room, Lottie.

# Functionallity 
App has general screen with button of starting game and the last game results (firslty, it won't appear, you have to play one game). The game screen has buttons to go back and restart the game. After you complete the game, the result will save to the local db.

# Package Structure

```
# App Module
.
├── presentation                  # Store MainActivity / Compose Screens / Theme / VM, etc.
│
├── data                          # Network / Database / DataSource / Repository Impl
│
├── domain                        # Repository Interface / UseCase / Models / Mappers
│
├── di                            # AppModule / DataModule
│
└── common                        # Constants
```
```
# Presentation package
.
├── components                    # Components used around all applications, like some items, top bars
│
├── theme                         # App Colors / App Theme / App Shapes / App Typography
│
└── ui                            # Screens / View Model / Screen Components / Screen Constants
```
# Naming Conventions
```
Class - ExampleExample
```
```
Function - exampleExample
```
```
Composable - ExampleExample
```
```
Composable Preview - ExampleScreenPreview
```
# Compose File Structure
```ExampleScreen.kt```

name for the composable func should be ```ExampleScreen```. 

If a composable is going to contain a lot of code - you should divide it into smaller composable functions ```sections```.

Sections should be called following this pattern: ```*screenName*Screen*SectionName*Section```, example: ```ExampleScreenScrollableSection```

```EexmpleScreen``` contains ```ExampleScreenContent``` wich handles only ui. All ViewModel invocations should happen inside ```ExampleScreen```

For example:
```
@Composable
fun ExampleScreen(modifier: Modifier) {
   val viewModel: ExampleViewModel = hiltViewModel()
   ExampleScreenContent(modifier, viewModel.state.value)
}

@Composable
fun ExampleScreenContent(modifier: Modifier, state: ExampleState) {
    Column(modifier) {
        ExampleScreenTopBarSection(modifier = Modifier)
        ExampleScreenScrollableSection(modifier = Modifier)
    }
}

@Composable
fun ExampleScreenTopBarSection(modifier: Modifier) {
    Row(modifier) {
        //code
    }
}

@Composable
fun ExampleScreenScrollableSection(modifier: Modifier) {
    Column(modifier) {
        //code
    }
}

@Preview
@Composable
fun ExampleScreenPreview() {
    ExampleScreenContent(modifier = Modifier, state = ExampleState.DefaultValue)
}
```
