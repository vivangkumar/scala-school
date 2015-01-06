/**
 * Object THreads
 * Scala Polymorphism concepts
 * @author Vivan
 */

import java.net.{Socket, ServerSocket}
import java.util.concurrent.{Executors, ExecutorService}
import java.util.Date

object Threads {
  def main(args: Array[String]) = {
    val hello = new Thread(new Runnable {
      def run() = println("Hello world!")
    })
    hello.start()
    // Start thread
    new NetworkService(2020, 2).run()
  }
}

// Simple network server
class NetworkService(port: Int, poolSize: Int) extends Runnable {
  val serverSocket = new ServerSocket(port)
  val pool: ExecutorService = Executors.newFixedThreadPool(poolSize)

  override
  def run() {
    try {
      while (true) {
        val socket = serverSocket.accept()
        pool.execute(new Handler(socket))
      }
    } finally {
      pool.shutdown()
    }
  }
}

class Handler(socket: Socket) extends Runnable {
  def message = Thread.currentThread.getName.getBytes
   override
  def run() {
     socket.getOutputStream.write(message)
     socket.getOutputStream.close()
   }
}

// Synchronization
class Person(var name: String) {
  def set(changedName: String): Unit = {
    this.synchronized {
      name = changedName
    }
  }
}

