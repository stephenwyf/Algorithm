package Order;

/**
 * Created by 65401 on 2017/3/24.
 * 手写HashMap
 * 有两个重要参数      装载因子        默认容量
 * 链地址法
 */
public class HashMap {

    //链表
    private class Entity{
        Entity next;//下一个元素的地址。
        Integer key;
        Integer value;
        int hash;
        Entity(Integer key,Integer value,int hash){
            this.key=key;
            this.value=value;
            this.hash=hash;
        }
    }

    private int size;// 当前容量
    private static int INIT_CAPACITY = 16;// 默认容量
    private Entity [] container=null;//装载元素的容器，是个对象数组
    private static float LOAD_FACTOR = 0.75f;//
    //装载因子:就是hash表中已经存储的关键字个数，
    //与可以散列位置的比值，表征着hash表中的拥挤情况，
    //一般而言，该值越大则越容易发生冲突，相应地ASL(平均查找长度)也增大
    private int max;// 能存的最大的数=capacity*factor 0.75越接近1，代表其越逼近16，也代表该容器越挤，
    //冲突越大

    /**
     * @param init_capacity:自定义的初始化容量
     * @param load_factor:自定义的加载因子
     */
    public HashMap(int init_capacity,float load_factor){
        INIT_CAPACITY=init_capacity;
        LOAD_FACTOR=load_factor;
        this.max= (int) (INIT_CAPACITY*LOAD_FACTOR);//      16*0.75=12
        container=new Entity[INIT_CAPACITY];//      开辟容器的空间  16
    }
    public HashMap(){
        this(INIT_CAPACITY,LOAD_FACTOR);
    }


    private boolean put(Integer key,Integer value){
        int hash=key.hashCode();//      调用JDK的方法来计算hash值
        Entity newEntity=new Entity(key,value,hash);//     新元素
        if(setEntity(newEntity,container)){
            this.size++;
            return true;
        }
        return false;
    }

    /**
     * @param temp:新元素
     * @param table:元素的集合
     * @return: true：增加成功，false：增加失败
     */
    private boolean setEntity(Entity temp,Entity[] table){
        //根据hash码，计算容器下标值,尽量保持唯一，如果不唯一，就得使用单链的形式存储冲突的元素，速度变慢
        int index=this.indexFor(temp.hash,table.length);
        //判断index指定的元素是否存在
        Entity entity=table[index];
        if(entity!=null){//     存在
            while(entity!=null){
                //判断是否和老元素相同  （key  hash  value）
                if((temp.key==entity.key||temp.key.equals(entity.key)) &&
                        temp.hash==entity.hash &&
                        (temp.value==entity.value||temp.value.equals(entity.value))){
                    return false;//     相等不添加

                }else if(entity.next==null){//可以添加元素了，添加到最后一个老元素的next，因为是个单链表
                    break;
                }

                entity=entity.next;
            }
            //添加新元素到链尾
            addLast(temp,entity);
        }
        //增加该元素
        setFirstEntry(temp,index,table);
        return true;
    }

    //根据hash码，容器数组的长度，计算找到该hash码在容器数组中的下标值
    private int indexFor(int hashcode,int containerLength){
        return hashcode & (containerLength-1);//    2&3  0010&0011=0010
    }

    /**
     * 将指定结点temp，添加到指定的hash表table的指定下标index中
     * @param temp ：新元素
     * @param index ：在数组中的索引号
     * @param table ：数组容器
     */
    private void setFirstEntry(Entity temp,int index,Entity[] table){
        if(size>max){
            this.reSize(table.length*4);//    开辟新空间
        }
        table[index]=temp;
        //因为每次设置后都是新的链表，需要将其后接的节点都去掉
        temp.next=null;
    }

    //扩容
    private void reSize(int newSize){
        Entity[] newEntity=new Entity[newSize];
        //重新计算max
        this.max= (int) (LOAD_FACTOR*newSize);
        //需要把老数组中的所有元素付给新数组
        for(int i=0;i<this.container.length;i++){
            Entity temp=this.container[i];//    老元素
            //因为每个元素都是以单链表形式存在的
            while(temp!=null){
                this.setEntity(temp,newEntity);//   把老数组的每个元素都送入到新数组中
                temp=temp.next;
            }
        }
        //把新数组的地址付给老数组
        this.container=newEntity;
    }

    /**
     * 添加新元素到链尾
     * @param temp:新元素
     * @param entity：老元素
     */
    private void addLast(Entity temp,Entity entity){
        //需要扩容
        if(size>max){
            reSize(container.length*4);
        }
        entity.next=temp;
    }

    //根据键取值
    private int get(Integer key){
        Entity temp=null;
        //找到索引
        int index=this.indexFor(key.hashCode(),this.container.length);
        temp=this.container[index];
        if(temp==null){//       没有值
            return -1;
        }else{
            //找到链上最后一个
            while(temp!=null){
                if(temp.key==key || temp.key.equals(key)){
                    return temp.value;
                }
                temp=temp.next;
            }
        }
        return -1;
    }




    private void test(){
        long start=System.currentTimeMillis();
        for(int i=0;i<1000000;i++){
            this.put(i,i);
        }
        this.put(13,14);
        long end=System.currentTimeMillis();
        System.out.println("need time :0."+(end-start));//    所得的时间
        System.out.println(get(13));
    }

    public static void main(String[] args) {
        HashMap hashMap=new HashMap();
        hashMap.test();
    }
}
