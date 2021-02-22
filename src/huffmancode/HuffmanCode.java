package huffmancode;


import java.util.*;

/**
 * @author HGAi
 * @date 2021/2/9 0009 16:40
 * 赫夫曼编码压缩原理：
 * 1、创建Node
 * 2、得到"i like like like java do you like a java" 对应的byte[] 数组
 * 3、编写一个方法，将准备构建赫夫曼树的Node 节点放到list
 * 4、通过list构建赫夫曼树
 */
public class HuffmanCode {
    public static void main(String[] args) {
        int a = -3;
        System.out.println((byte)a);
/*        String str = "i like like like java do you like a java";
        byte[] bytes = str.getBytes();
        System.out.println("bytes长度 >>> " + bytes.length);
        byte[] huffmanZip = huffmanZip(bytes);
        System.out.println("压缩后 >>> " + Arrays.toString(huffmanZip));*/
        //分布过程
/*        List<Node> nodes = getNodes(bytes);
        System.out.println("nodes >>> " + nodes);
        Node tree = createHuffmanTree(nodes);
        System.out.println("赫夫曼树前序遍历 >>> ");
        preOrder(tree);
        Map<Byte, String> huffmanCodes = getCodes(tree);
        System.out.println("生成的赫夫曼码表 >>> " + huffmanCodes);
        byte[] huffmanCodeBytes = zip(bytes, huffmanCodes);
        System.out.println("压缩后 >>> " + Arrays.toString(huffmanCodeBytes));*/
    }

    /**
     * 将 一个 byte 转成 一个二进制的字符串
     * @param b
     * @return
     */
  /*  private static String byteToBitString(byte b) {

    }*/

    private static Map<Byte, String> getCodes(Node root) {
        if (root == null) {
            return null;
        }
        // 处理左子树
        getCodes(root.left,"0", path);
        // 处理右子树
        getCodes(root.right, "1", path);
        return huffmanCodes;
    }

    /**
     * 赫夫曼字节数组封装
     * @param bytes 原始的字符串对应的字节数组
     * @return 经过赫夫曼编码 处理后的字节数组
     */
    private static byte[] huffmanZip(byte[] bytes) {
        List<Node> nodes = getNodes(bytes);
        // 根据nodes 创建 赫夫曼树
        Node tree = createHuffmanTree(nodes);
        // 生成赫夫曼编码
        Map<Byte, String> huffmanCodes = getCodes(tree);
        // 根据生成的赫夫曼编码压缩后的赫夫曼编码字节数组
        byte[] huffmanCodeBytes = zip(bytes, huffmanCodes);

        return huffmanCodeBytes;
    }

    /**
     * 将字符串对应的byte[] 数组，通过生成的赫夫曼编码表，返回一个赫夫曼编码 压缩后的byte
     *
     * @param bytes 原始的字符串对应的byte[]
     * @param huffmanCodes 生成的赫夫曼编码map
     * @return 返回赫夫曼编码处理后的byte[]
     * 举例：String content = "i like like java do you like a java"
     * byte [] contentBytes = content.getBytes();
     * 返回的字符串"1010100010111111110010001011111111001000101111111100100101001101110001110000011011101000111100101000101111111100110001001010011011100"
     * => 对应的 byte[] huffmanCodeBytes，即8位对应一个byte,放入到huffmanCodeBytes
     * huffmanCodeBytes[0] = 1010100（补码） => byte [推导 补码 -> 反码 -> 原码] -88
     */
    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
        // 1、利用huffmanCodes 将 bytes 转成 赫夫曼编码对应的字符串
        StringBuilder stringBuilder = new StringBuilder();
        // 遍历bytes 数组
        for (byte b : bytes) {
            stringBuilder.append(huffmanCodes.get(b));
        }
        System.out.println("测试 stringBuilder = " + stringBuilder.toString());

        // 将 "1010100.." 转成 byte[]
        // 统计返回 byte[] huffmanCodeBytes长度
        int len;
        if (stringBuilder.length() % 8 == 0) {
            len = stringBuilder.length() / 8;
        } else {
            len = stringBuilder.length() / 8 + 1;
        }
        // 创建 存储压缩后的byte数组
        byte[] huffmanCodeBytes = new byte[len];
        // 记录是第几个byte
        int index = 0;
        for (int i = 0; i < stringBuilder.length(); i += 8) {
            String strByte;
            if (i + 8 > stringBuilder.length()) {
                // 不够8位
                strByte = stringBuilder.substring(i);
            } else {
                strByte = stringBuilder.substring(i, i + 8);
            }
            // 将strByte 转成 一个 byte，放入到 huffmanCodeBytes
            huffmanCodeBytes[index] = (byte) Integer.parseInt(strByte,2);
            index ++;
        }
        return huffmanCodeBytes;
    }

    /**
     * 生成赫夫曼树对应的赫夫曼编码思路：
     * 存放赫夫曼编码 Map<Byte, String> map
     * 拼接赫夫曼编码 StringBuilder
     */
    static Map<Byte, String> huffmanCodes = new HashMap<>();
    static StringBuilder path = new StringBuilder();

    /**
     * 功能：将传入的Node节点的所有叶子节点得到赫夫曼编码，并放入huffmanCodes集合
     * @param node 传入节点
     * @param code 路径：左0 右1
     * @param path 拼接路径
     */
    private static void getCodes(Node node, String code, StringBuilder path) {
        StringBuilder stringBuilder = new StringBuilder(path);
        stringBuilder.append(code);
        if (node != null) {
            if (node.data == null) {
                getCodes(node.left, "0", stringBuilder);
                getCodes(node.right, "1", stringBuilder);
            } else {
                huffmanCodes.put(node.data, stringBuilder.toString());
            }
        }
    }

//    @Override
    public static void preOrder(Node node) {
       if (node != null) {
           node.preOrder();
       } else {
           System.out.println("赫夫曼树为空，无法遍历");
       }
    }

    private static List<Node> getNodes(byte[] bytes) {
        // 1、 创建一个ArrayList
        List<Node> nodes = new ArrayList<>();
        // 2、 遍历bytes，统计每一个byte出现的次数，放入到Map中
        Map<Byte, Integer> map =  new HashMap<>();
        for (byte b : bytes) {
            Integer count = map.get(b);
            if (count == null) {
                map.put(b, 1);
            } else {
                map.put(b, count + 1);
            }
        }
        // 3、把每一个键值对转成Node对象，放入nodes中
        map.forEach((key, value) -> {
            nodes.add(new Node(key, value));
        });
        return nodes;
    }

    public static Node createHuffmanTree(List<Node> nodes) {
        while (nodes.size() > 1) {
            // 从小到大
            Collections.sort(nodes);
            Node left = nodes.get(0);
            Node right = nodes.get(1);
            //创建新的二叉树, 没有data, 只有权值
            Node parent = new Node(null, left.weight + right.weight);
            parent.left = left;
            parent.right = right;
            nodes.remove(left);
            nodes.remove(right);
            nodes.add(parent);
        }
        return nodes.get(0);
    }
}

/**
 * 创建Node
 */
class Node implements Comparable<Node> {
    /**
     * 存放数据本身 例如 'a'=>97, ' ' =>32
     */
    Byte data;
    /**
     * 权值, 表示字符出现的次数
     */
    int weight;
    Node left;
    Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
        // 从小到大排序
        return this.weight - o.weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }
    // 前序遍历
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }
}