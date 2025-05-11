package linkedlist;

/**
 * 단순 연결 리스트
 * @instance variable
 * data = 데이터 값 저장 변수
 * link = 다음 노드 참조값 저장 변수
 *
 */
public class Node {
    Object data;
    Node link;

    Node(Object data){
        this.data = data;
        this.link = null;
    }
}
