import com.rabbitmq.client.Channel
import com.rabbitmq.client.Connection
import com.rabbitmq.client.ConnectionFactory

def Connection conn = null
def Channel channel
def ConnectionFactory factory = null

if (factory == null || conn == null) {
    factory = new ConnectionFactory()
    factory.username = "username"
    factory.password ="password"

    factory.setVirtualHost("/")
    factory.host =localhost

    factory.setPort(5672)
    conn = factory.newConnection()
}

channel = conn.createChannel()

channel.exchangeDeclare("github.inquiry.state", "topic", true)
channel.queueDeclare("github.inquiry.state", true, false, false, null)
channel.queueBind("github.inquiry.state", "github.inquiry.state", "github.inquiry.rkey")

channel.close()
conn.close()
