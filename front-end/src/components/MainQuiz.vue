<template>
    <p v-if="fetchMsg">{{fetchMsg}}</p>
     <!-- Loading animation while waiting for API fetching  -->
    <div v-if="questions.length < 25" class='w-1/4' id="loading-animation"></div>
    <!-- Main Quiz -->
    <section v-else-if="!isSubmitted && questions.length" class="relative z-10 w-full h-full pb-14">
        <!-- Time Countdown Animation ---->
        <TimeCount @toSubmit="toggleIsSubmitted"/>
        
        <!-- Question Number List ---->
        <div class="flex justify-center gap-4 mb-10">
            <QuestNumber v-for="questionItem in questionNumberList" :key="questionItem.number" :number="questionItem.number" @clickQuestion="handleQuestionClick" :state="questionItem.state"/>
        </div>


        <!-- Question Section---->        
         <div class="z-10 flex flex-col items-center w-9/12 mx-auto from-gray-300 pb-14 text-gray-50 rounded-xl h-[65vh] overflow-y-scroll bg-gradient-to-br to-gray-400 pt-9 form-container">

            <!-- Question --->
            <h1 class="text-3xl font-bold text-center select-none ">{{questions[currentQuestionIndex].questionText}}</h1>
            

            <!-- Option List --->
            <div class='flex flex-col w-9/12 gap-8 mx-auto my-12 px-14 '>
                <Option v-for=" (choice, index) in questions[currentQuestionIndex].choices" :key="index" :optionText="choice.choiceText" :questionNumber="questions[currentQuestionIndex].questionNumber" :option="choice.option" :hasChecked="checkValue" @optionSelected ="handleSelected" />
            </div>
            

            <div class='flex items-center gap-14'>
                <a href="#"  @click="toPreviousQuestion" class='text-xl px-7 opacity-80'>Previous question</a>
                <a href="#" @click="toNextQuestion" class="inline-block py-3 text-xl font-bold rounded-full px-7 bg-gradient-to-r from-red-600 to-pink-600 ">{{buttonText}}</a>
            </div>
            
        </div>


    </section>

   


    <Submit v-else :submitPayLoad="submitPayLoad" :formattedPayLoad="formattedPayLoad" @backToQuiz="toggleIsSubmitted"/>

</template>

<script>


import {useRouter} from 'vue-router'
import {computed, onMounted, ref, reactive, onUpdated, watchEffect} from 'vue'
import TimeCount from "./TimeCount.vue"
import QuestNumber from "./QuestNumber.vue"
import Option from "./Option.vue"
import Submit from "./Submit.vue"
import QuizAPI from "../services/QuizAPI"
import lottie from "lottie-web";
import LoadingAnimation from '../assets/loading-animation.json'

export default {
    components: {TimeCount, QuestNumber, Option, Submit},
    props: {
        quizCode: String,
        groupName: String,
        },
    setup(props, context) {
        onMounted(() => {
      lottie.loadAnimation({
                container: document.getElementById("loading-animation"), 
                renderer: "svg",
                loop: true,
                autoplay: true,
                //path: "https://assets7.lottiefiles.com/packages/lf20_xz75lvff.json",
                animationData: LoadingAnimation,
            })
    })
        const isSubmitted = ref(false);
        const currentQuestionIndex = ref(0);
        const submitPayLoad = reactive({
            quizCode: props.quizCode,
            groupName: props.groupName,
            answers: []

        })
        const fetchMsg = ref(null);
        const questions = ref([]);
        const questionNumberList = ref([]);

        const loadQuiz = async () => {
            try {
                let response = await QuizAPI.getQuiz(props.quizCode);
               
                
                questions.value = response.data.quiz.questions;
                console.log(questions.value)
            //     questionNumberList.value = questions.value.map((element,index) => (
            // index=== 0? {number: (index+1).toString(), state:'active'} :{number: (index+1).toString(), state:'default'}
            // ))

                 //Initialize the submit payload once questions.value are fetched.
                initSubmitPayload();

            } catch (err) {
                console.log(err.response);
                fetchMsg.value = "Oops, something wrong with the server. Please refresh the page!"
                
            }
        }
        loadQuiz();

        
        

        


        
        
        

        const initSubmitPayload = () => {
            
            questions.value.forEach((item, index) => {
                submitPayLoad.answers.splice(index, 0, {questionText: item.questionText, questionNumber: item.questionNumber, choiceText: 'N/A', answer: 'N/A'});
                

                if (index ===0){
                    questionNumberList.value.push({number: (index+1).toString(), state:'active'});

                }else{
                    questionNumberList.value.push({number: (index+1).toString(), state:'default'});
                }



                
            });

            

            console.log(submitPayLoad);
        }
        
       


        
        
        //const currentQuestionNumber = computed(() => currentQuestionIndex.value + 1);
        const buttonText = computed(() => questions.value[currentQuestionIndex.value].questionNumber === questions.value.length? "Go to Submit" : "Next question" );
    
        const updateQuestionState = (updateState) => {
            questionNumberList.value[currentQuestionIndex.value].state = updateState;
        }
        
        //handle any option selecting event change.
        const optionSelected = ref('N/A');
        const handleSelected = (option) => {
            optionSelected.value = option;
            
        }
             
        const handleQuestionClick = (el) => {
            selectAns();
            currentQuestionIndex.value = parseInt(el.innerText) - 1;
            updateQuestionState('active');
        };

        

        const selectAns = () => {
           
        
        if (optionSelected.value !== 'N/A'){
                let choiceText = document.querySelector(`label[for="${optionSelected.value}"]`).innerText;
                submitPayLoad.answers[currentQuestionIndex.value].answer = optionSelected.value;
                submitPayLoad.answers[currentQuestionIndex.value].choiceText = choiceText;
                console.log(submitPayLoad.answers[currentQuestionIndex.value]);
                optionSelected.value = 'N/A';
                updateQuestionState('done');

            }else{
                updateQuestionState('default');
            }
            
            
            
        }


        const toNextQuestion = () => {
            selectAns();

            

            if (currentQuestionIndex.value < (questions.value.length - 1) ){
 
                currentQuestionIndex.value+=1; 
                
                updateQuestionState('active');
            }else{
                
                isSubmitted.value = true;
            }
            
            
            
        }

        const toPreviousQuestion = () => {
            selectAns();
            if (currentQuestionIndex.value > 0 ){
                
                currentQuestionIndex.value-=1; 
                
                updateQuestionState('active');
            }
            
        }

        
        const checkValue = computed(() => {
           
            let answer  = submitPayLoad.answers.find(answer => answer.questionNumber === questions.value[currentQuestionIndex.value].questionNumber);
           
            if (answer && answer.answer){
                return answer.answer;
                
            }else{
                return "init";
            }
            
           
            
        })
        
        
        const toggleIsSubmitted = (state) => {
            if (state) {
                selectAns();
                
            };
            isSubmitted.value = state;
        }

        const formattedAnwser = () => {
            return submitPayLoad.answers.map((item) => ({
                answer: item.answer === 'N/A'? null: item.answer,
                questionNumber: item.questionNumber
            }))
        }


        const formattedPayLoad = {...submitPayLoad, answers: formattedAnwser()}
        

        const submit = () => {
            QuizAPI.submitQuiz(formattedPayLoad)
            .then(response => console.log(response))
            .catch(error => console.log(error.response))
        }




        window.onbeforeunload = confirmExit;
        function confirmExit() {
            submit();
            return "Are you sure you want to exit?"
        }
        
        return{handleQuestionClick, questions, currentQuestionIndex, selectAns, toNextQuestion, toPreviousQuestion, questionNumberList, checkValue, buttonText, isSubmitted, submitPayLoad, toggleIsSubmitted, handleSelected, fetchMsg, formattedPayLoad}
    },
}
</script>