console.log("dept Module........");

console.log("dept Module........");

const deptService = (function() {

    // 부서 추가 함수
    function add(dept, callback, error) {
        console.log("> deptService.add().........");

        $.ajax({
            type: 'POST',
            url: '/scott/dept/new',
            data: JSON.stringify(dept),
            contentType: "application/json; charset=utf-8",
            cache: false,
            success: function(result, status, xhr) {
                if (callback) {
                    callback(result);
                }
            },
            error: function(xhr, status, er) {
                if (error) {
                    error(er);
                }
            }
        });
    } // add 함수 종료

    

   // delete 요청방식 사용버전
   // delete?50 이렇게 넘어올 수도 있음! 대신 요청이 GET/POST/DELETE/PUT 등등으로 의미를 전달해줘야함 ( 레스트 풀 api )
   /* 방법 1번
    function remove(deptno, callback, error) {
        console.log("> deptService.remove().........");

        $.ajax({
            type: 'get',
            url: '/scott/dept/remove',
            data: `deptno=${deptno}`,
            cache: false,
            success: function(result, status, xhr) {
                if (callback) {
                    callback(result);
                }
            },
            error: function(xhr, status, er) {
                if (error) {
                    error(er);
                }
            }
        });
    } //remove 함수 종료
    */
    
    

	// 방법 2번 RESTful 
  	function remove(deptno, callback, error) {
        console.log("> deptService.remove().........");

        $.ajax({
            type: 'delete',
            url: `/scott/dept/${deptno}`,
            data: `deptno=${deptno}`,
            cache: false,
            success: function(result, status, xhr) {
                if (callback) {
                    callback(result);
                }
            },
            error: function(xhr, status, er) {
                if (error) {
                    error(er);
                }
            }
        });
    } //remove 함수 종료
    

    return {
        add: add,
        remove: remove
    };

})(); // deptService 함수 종료
