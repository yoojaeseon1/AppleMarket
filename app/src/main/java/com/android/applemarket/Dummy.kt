package com.android.applemarket

class Dummy (){


    companion object {
        val posts = mutableListOf<Post>()
        var loginedUser = User()
    }

    init {

        val user1 = User("id1", "password1", "대현동", "서울 서대문구 창천동")
        val user2 = User("id2", "password2", "안마담", "인천 계양구 귤현동")
        val user3 = User("id3", "password3", "코코유", "수성구 범어동")
        val user4 = User("id4", "password4", "Nicole", "해운대구 우제2동")
        val user5 = User("id5", "password5", "절명", "연제구 연산제8동")
        val user6 = User("id6", "password6", "미니멀하게", "수원시 영통구 원천동")
        val user7 = User("id7", "password7", "굿리치", "남구 옥동")
        val user8 = User("id8", "password8", "난쉽", "동래구 온천제2동")
        val user9 = User("id9", "password9", "알뜰한", "원주시 명륜2동")
        val user10 = User("id10", "password10", "똑태현", "중구 동화동")

        loginedUser = user1

        val post1 = Post(user1, R.drawable.sample1, "산지 한달된 선풍기 팝니다", "이사가서 필요가 없어졌어요 급하게 내놓습니다",  10000,  25)
        val post2 = Post(user2, R.drawable.sample2, "김치냉장고", "이사로인해 내놔요",  20000,  28)
        val post3 = Post(user3, R.drawable.sample3, "샤넬 카드지갑", "고퀄지갑이구요\n사용감이 있어서 싸게 내어둡니다",  10000,  5 )
        val post4 = Post(user4, R.drawable.sample4, "금고", "금고\n떼서 가져가야함\n대우월드마크센텀\n미국이주관계로 싸게 팝니다",  10000, 17)
        val post5 = Post(user5, R.drawable.sample5, "갤럭시Z플립3 팝니다", "갤럭시 Z플립3 그린 팝니다\n항시 케이스 씌워서 썻고 필름 한장챙겨드립니다\n\n화면에 살짝 스크래치난거 말고 크게 이상은없습니다!",  150000,  9)
        val post6 = Post(user6, R.drawable.sample6, "프라다 복조리백", "까임 오염없고 상태 깨끗합니다\n\n정품여부모름",  50000,  16)
        val post7 = Post(user7, R.drawable.sample7, "울산 동해오션뷰 60평 복층 펜트하우스 1일 숙박권 펜션 힐링 숙소 별장", "울산 동해바다뷰 60평 복층 펜트하우스 1일 숙박권\n\n(에어컨이 없기에 낮은 가격으로 변경했으며 8월 초 가장 더운날 다녀가신 분 경우 시원했다고 잘 지내다 가셨습니다)\n\n1. 인원: 6명 기준입니다. 1인 10,000원 추가요금\n\n2. 장소: 북구 블루마시티, 32-33층\n\n3. 취사도구, 침구류, 세면도구, 드라이기 2개, 선풍기 4대 구비\n\n4. 예약방법: 예약금 50,000원 하시면 저희는 명함을 드리며 입실 오전 잔금 입금하시면 저희는 동.호수를 알려드리며 고객님은 예약자분 신분증 앞면 주민번호 뒷자리 가리시거나 지우시고 문자로 보내주시면 저희는 카드키를 우편함에 놓아 둡니다.\n\n5. 33층 옥상 야외 테라스 있음, 가스버너 있음\n\n6. 고기 굽기 가능\\n7. 입실 오후 3시, 오전 11시 퇴실, 정리, 정돈 , 밸브 잠금 부탁드립니다.\n\n8. 층간소음 주의 부탁드립니다.\n\n9. 방3개, 화장실3개, 비데 3개\n\n10. 저희 집안이 쓰는 별장입니다.", 150000, 54)
        val post8 = Post(user8, R.drawable.sample8, "샤넬 탑핸들 가방", "샤넬 트랜디 CC 탑핸들 스몰 램스킨 블랙 금장 플랩백 !\n\n색상 : 블랙\n + 사이즈 : 25.5cm * 17.5cm * 8cm\n 구성 : 본품더스트\n\n급하게 돈이 필요해서 팝니다 ㅠ ㅠ",  180000,  7)
        val post9 = Post(user9, R.drawable.sample9, "4행정 엔진분무기 판매합니다.", "3년전에 사서 한번 사용하고 그대로 둔 상태입니다. 요즘 사용은 안해봤습니다. 그래서 저렴하게 내 놓습니다. 중고라 반품은 어렵습니다.\n\n", 30000,  28)
        val post10 = Post(user10, R.drawable.sample10, "셀린느 버킷 가방", "22년 신세계 대전 구매입니당\n\n셀린느 버킷백\n\n구매해서 몇번사용했어요\n\n까짐 스크래치 없습니다.\n\n타지역에서 보내는거라 택배로 진행합니당!\n",  190000,  6)


//        post1.likes.add(Like(user1))



        posts.add(post1)
        posts.add(post2)
        posts.add(post3)
        posts.add(post4)
        posts.add(post5)
        posts.add(post6)
        posts.add(post7)
        posts.add(post8)
        posts.add(post9)
        posts.add(post10)
    }


}