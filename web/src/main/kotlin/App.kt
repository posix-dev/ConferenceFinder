import conferences.model.Conference
import conferences.utils.Result
import react.*
import react.dom.*
import kotlinx.coroutines.*

val App = functionalComponent<RProps> { _ ->
    val appDependencies = useContext(AppDependenciesContext)
    val repository = appDependencies.repository

    val (conference, setConference) = useState(emptyList<Conference>())

    useEffectWithCleanup(dependencies = listOf()) {
        val mainScope = MainScope()

        mainScope.launch {
            val fetchConferences = repository.fetchConferences()
            if (fetchConferences is Result.Success) {
                val data = fetchConferences.data
                setConference(data)
            }
        }
        return@useEffectWithCleanup { mainScope.cancel() }
    }

    h1 {
        +"Conferences"
    }
    ul {
        conference.forEach { item ->
            li {
                +"${item.name} (${item.status})"
            }
        }
    }
}