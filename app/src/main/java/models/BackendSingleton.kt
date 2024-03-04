package models

object BackendSingleton {

    // Фиктивный список категорий для тестирования
    private val categoriesList = listOf(
        Category(1, "Бургеры", "Описание категории 1", "burger.png"),
        Category(2, "Дессерты", "Описание категории 2", "dessert.png"),
        Category(3, "Рыба", "Описание категории 3", "fish.png"),
        Category(4, "Пицца", "Описание категории 4", "pizza.png"),
        Category(5, "Салаты", "Описание категории 5", "salad.png"),
        Category(6, "Супы", "Описание категории 6", "soup.png"),
    )

    // Метод для получения списка категорий
    fun getCategories(): List<Category> {
        return categoriesList
    }

    // Дополнительные методы для работы с бэкендом можно добавить здесь
}
