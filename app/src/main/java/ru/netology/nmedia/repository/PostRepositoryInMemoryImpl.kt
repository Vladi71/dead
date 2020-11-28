package ru.netology.nmedia.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.dto.Post

class PostRepositoryInMemoryImpl : PostRepository {
    private var nextId = 1L
    private var posts = listOf(
            Post(
                    id = nextId++,
                    author = "Нетология. Университет интернет-профессий будущего",
                    content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
                    published = "21 мая в 18:36",
                    numberLike = 1099,
                    numberShare = 12,
                    numberViews = 999_999,
                    likeByMy = false


            ),
            Post(
                    id = nextId++,
                    author = "Нетология. Университет интернет-профессий будущего",
                    content = "Делиться впечатлениями о любимых фильмах легко, а что если рассказать так, чтобы все заскучали \uD83D\uDE34\n",
                    published = "22 сентября в 10:14",
                    numberLike = 10546,
                    numberShare = 985,
                    numberViews = 3215,
                    likeByMy = false
            ),
            Post(
                    id = nextId++,
                    author = "Нетология. Университет интернет-профессий будущего",
                    content = "Таймбоксинг — отличный способ навести порядок в своём календаре и разобраться с делами, которые долго откладывали на потом. Его главный принцип — на каждое дело заранее выделяется определённый отрезок времени. В это время вы работаете только над одной задачей, не переключаясь на другие. Собрали советы, которые помогут внедрить таймбоксинг \uD83D\uDC47\uD83C\uDFFB",
                    published = "22 сентября в 10:12",
                    numberLike = 215,
                    numberShare = 87,
                    numberViews = 100585,
                    likeByMy = false
            ),
            Post(
                    id = nextId++,
                    author = "Нетология. Университет интернет-профессий будущего",
                    content = "\uD83D\uDE80 24 сентября стартует новый поток бесплатного курса «Диджитал-старт: первый шаг к востребованной профессии» — за две недели вы попробуете себя в разных профессиях и определите, что подходит именно вам → http://netolo.gy/fQ",
                    published = "21 сентября в 10:12",
                    numberLike = 87,
                    numberShare = 20,
                    numberViews = 665458,
                    likeByMy = false
            ),
            Post(
                    id = nextId++,
                    author = "Нетология. Университет интернет-профессий будущего",
                    content = "Диджитал давно стал частью нашей жизни: мы общаемся в социальных сетях и мессенджерах, заказываем еду, такси и оплачиваем счета через приложения.",
                    published = "20 сентября в 10:14",
                    numberLike = 1000000006,
                    numberShare = 5450,
                    numberViews = 65,
                    likeByMy = false
            ),
            Post(
                    id = nextId++,
                    author = "Нетология. Университет интернет-профессий будущего",
                    content = "Большая афиша мероприятий осени: конференции, выставки и хакатоны для жителей Москвы, Ульяновска и Новосибирска \uD83D\uDE09",
                    published = "19 сентября в 14:12",
                    numberLike = 9854,
                    numberShare = 321,
                    numberViews = 999_654,
                    likeByMy = false
            ),
            Post(
                    id = nextId++,
                    author = "Нетология. Университет интернет-профессий будущего",
                    content = "Языков программирования много, и выбрать какой-то один бывает нелегко. Собрали подборку статей, которая поможет вам начать, если вы остановили свой выбор на JavaScript.",
                    published = "19 сентября в 10:24",
                    numberLike = 87,
                    numberShare = 12,
                    numberViews = 654,
                    likeByMy = false

            ),
            Post(
                    id = nextId++,
                    author = "Нетология. Университет интернет-профессий будущего",
                    content = "Знаний хватит на всех: на следующей неделе разбираемся с разработкой мобильных приложений, учимся рассказывать истории и составлять PR-стратегию прямо на бесплатных занятиях \uD83D\uDC47",
                    published = "18 сентября в 10:12",
                    numberLike = 5648,
                    numberShare = 23,
                    numberViews = 999_999,
                    likeByMy = false
            ),
            Post(
                    id = nextId++,
                    author = "Нетология. Университет интернет-профессий будущего",
                    content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
                    published = "21 мая в 18:36",
                    numberLike = 1099,
                    numberShare = 12,
                    numberViews = 999_999,
                    likeByMy = false,
                    video = "https://www.youtube.com/watch?v=kyWD_FruUGk&ab_channel=%D0%9E%D0%B1%D1%80%D0%B0%D0%B7%D0%BE%D0%B2%D0%B0%D1%82%D0%B5%D0%BB%D1%8C%D0%BD%D0%B0%D1%8F%D0%BF%D0%BB%D0%B0%D1%82%D1%84%D0%BE%D1%80%D0%BC%D0%B0%D0%9D%D0%B5%D1%82%D0%BE%D0%BB%D0%BE%D0%B3%D0%B8%D1%8F"

            )
    ).reversed()
    private val data = MutableLiveData(posts)
    override fun getAll(): LiveData<List<Post>> = data

    override fun savePost(post: Post) {
        if (post.id == 0L) {

            posts = listOf(
                    post.copy(
                            id = nextId++,
                            author = "Нетология. Университет интернет-профессий будущего",
                            published = "09 ноября",
                            likeByMy = false,
                            numberLike = 0,
                            numberViews = 0,
                            numberShare = 0
                    )
            ) + posts
            data.value = posts
            return
        }

        posts = posts.map {
            if (it.id != post.id) it else it.copy(content = post.content, video = post.video)
        }

        data.value = posts
    }

    override fun likeId(id: Long) {
        posts = posts.map {
            if (it.id != id) {
                it
            } else {
                it.copy(
                        likeByMy = !it.likeByMy, numberLike = if (it.likeByMy) it.numberLike - 1
                else it.numberLike + 1
                )
            }
        }
        data.value = posts
    }


    override fun viewId(id: Long) {
        posts = posts.map {
            if (it.id != id) {
                it
            } else {
                it.copy(numberViews = it.numberViews + 1)
            }
        }
        data.value = posts
    }


    override fun removeById(id: Long) {
        posts = posts.filter { it.id != id }
        data.value = posts
    }

    override fun editById(id: Long, content: String) {
        val index = posts.indexOfFirst { it.id == id }.takeIf { it >= 0 } ?: return
        posts = posts.toMutableList().apply {
            set(index, posts[index].copy(content = content))
        }
        data.value = posts
    }

}

