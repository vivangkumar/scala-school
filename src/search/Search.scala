package search

/**
 * Class InvertedIndex
 * @author Vivan
 */

import scala.collection.mutable
import java.util.concurrent._
import scala.collection.JavaConversions._
import scala.collection.JavaConverters._

import scala.io.Source

case class User(name: String, id: Int)

class InvertedIndex(val userMap: mutable.Map[String, User]) {
  def this() = this(new mutable.HashMap[String, User])

  def tokenizeName(name: String): Seq[String] = {
    name.split(" ").map(_.toLowerCase)
  }

  def add(term: String, user: User): Unit = {
    userMap += term -> user
  }

  def add(user: User): Unit = {
    val tokens = tokenizeName(user.name)

    tokens.foreach { term =>
      userMap.synchronized {
        add(term, user)
      }
    }
  }
}

class ConcurrentInvertedIndex(userMap: ConcurrentMap[String, User]) extends InvertedIndex(userMap) {
  def this() = this(new ConcurrentHashMap[String, User] asScala)
}

// Concrete producer
class Producer[T](path: String, queue: BlockingQueue[T]) extends Runnable {
  def run(): Unit = {
    Source.fromFile(path, "utf-8").getLines().foreach { line =>
      queue.put(line) // TODO
    }
  }
}

// Abstract consumer
abstract class Consumer[T](queue: BlockingQueue[T]) extends Runnable {
  def run: Unit = {
    while(true) {
      val item = queue.take()
      consume(item)
    }
  }

  def consume(x: T)
}

object Execute {
  def main(args: Array[String]): Unit = {
    val queue = new LinkedBlockingQueue[String]()

    // One thread for Producer
    val producer = new Producer[String]("users.txt", queue)
    new Thread(producer).start()

    // Assume 8 cores
    val cores = 8
    val pool = Executors.newFixedThreadPool(cores)

    // One consumer per core
    for(i <- i to cores) {
      pool.submit(new IndexerConsumer[String](index, queue)) // TODO
    }
  }
}

trait UserMaker {
  def makeUser(line: String) = line.split(",") match {
    case Array(name, userid) => User(name, userid.trim().toInt)
  }
}

class IndexerConsumer(index: InvertedIndex, queue: BlockingQueue[String]) extends Consumer[String](queue) with UserMaker {
  def consume(t: String) = index.add(makeUser(t))
}

