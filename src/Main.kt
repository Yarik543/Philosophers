enum class Philosopher(val id: Int) {
    PHILOSOPHER_1(0),
    PHILOSOPHER_2(1),
    PHILOSOPHER_3(2),
    PHILOSOPHER_4(3),
    PHILOSOPHER_5(4);
}

class Table(private val philosophers: List<Philosopher>) {
    private val forks: BooleanArray = BooleanArray(philosophers.size) { false }//false - не взята

    fun tryToEat() {
        val order = philosophers.shuffled() // Случайный порядок философов
        val eatingPhilosophers = mutableListOf<Philosopher>()//философы, которые смогли взять обе вилки

        for (philosopher in order) {
            val leftFork = (philosopher.id + philosophers.size - 1) % philosophers.size
            val rightFork = philosopher.id

            if (!forks[leftFork] && !forks[rightFork]) {
                // Забираем обе вилки
                forks[leftFork] = true
                forks[rightFork] = true

                eatingPhilosophers.add(philosopher)
                println("${philosopher.name} берет вилки и начинает обедать.")
            } else {
                println("${philosopher.name} не смог взять обе вилки и продолжает размышлять.")
            }
        }

        printResults(eatingPhilosophers)
    }

    private fun printResults(eatingPhilosophers: List<Philosopher>) {
        println("\nИтоги:")
        for (philosopher in philosophers) {
            if (philosopher in eatingPhilosophers) {
                println("${philosopher.name} обедает.")
            } else {
                println("${philosopher.name} размышляет.")
            }
        }
    }
}

fun main() {
    val philosophers = Philosopher.values().toList()//создаем список философов, возвращает массив всех значений перечисления Philosophers
    val table = Table(philosophers)

    table.tryToEat() // Только один раунд
}