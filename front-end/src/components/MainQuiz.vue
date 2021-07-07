<template>

    <section v-if="!isSubmitted" class="relative z-10 w-full">
        <!-- Time Countdown Animation ---->
        <TimeCount @toSubmit="toggleIsSubmitted"/>
        
        <!-- Question Number List ---->
        <div class="flex justify-center gap-4 mb-10">
            <QuestNumber v-for="questionItem in questionNumberList" :key="questionItem.number" :number="questionItem.number" @clickQuestion="handleQuestionClick" :state="questionItem.state"/>
        </div>


        <!-- Question Section---->        
         <div class="z-10 flex flex-col items-center w-9/12 mx-auto from-gray-300 pb-14 text-gray-50 rounded-xl bg-gradient-to-br to-gray-400 pt-9 form-container">

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

    <Submit v-else :submitPayLoad="submitPayLoad" @backToQuiz="toggleIsSubmitted"/>
   
</template>

<script>


import {useRouter} from 'vue-router'
import {computed, onMounted, ref, reactive, onUpdated, watchEffect} from 'vue'
import TimeCount from "./TimeCount.vue"
import QuestNumber from "./QuestNumber.vue"
import Option from "./Option.vue"
import Submit from "./Submit.vue"
import QuizAPI from "../services/QuizAPI"
const loadQuiz = async (quizCode) => {
            try {
                const response = await QuizAPI.getQuiz(quizCode);
                
                return response.data.quiz.questions;

            } catch (err) {
                console.log(err.response);
                return;
            }
}
export default {
    components: {TimeCount, QuestNumber, Option, Submit},
    props: {
        quizCode: String,
        groupName: String,
        },
    async setup(props, context) {
        const questions = await loadQuiz(props.quizCode);
        const isSubmitted = ref(false);
        const currentQuestionIndex = ref(0);
        

        
        console.log(questions);
        const submitPayLoad = reactive({
            quizCode: props.quizCode,
            data: []

        })

        const initSubmitPayload = () => {
            
            questions.forEach((item, index) => {
                submitPayLoad.data.splice(index, 0, {questionText: item.questionText, questionNumber: index+1, optionText: 'N/A', optionSelected: 'N/A'});
            });

            console.log(submitPayLoad);
        }
        
        //Initialize the submit payload once questions.value are fetched.
        initSubmitPayload();


        
        const questionNumberList = ref(questions.map((element,index) => (
            index=== 0? {number: (index+1).toString(), state:'active'} :{number: (index+1).toString(), state:'default'}
            )));
        //const currentQuestionNumber = computed(() => currentQuestionIndex.value + 1);
        const buttonText = computed(() => questions[currentQuestionIndex].questionNumber === questions.value.length? "Go to Submit" : "Next question" );
    
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
                let optionText = document.querySelector(`label[for="${optionSelected.value}"]`).innerText;
                submitPayLoad.data[currentQuestionIndex.value].optionSelected = optionSelected.value;
                submitPayLoad.data[currentQuestionIndex.value].optionText = optionText;
                
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
           
            // let answer  = submitPayLoad.data.find(answer => answer.questionNumber === questions[currentQuestionIndex].questionNumber);
           
            if (answer && answer.optionSelected){
                return answer.optionSelected;
                
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
        
        return{handleQuestionClick, questions, currentQuestionIndex, selectAns, toNextQuestion, toPreviousQuestion, questionNumberList, checkValue, buttonText, isSubmitted, submitPayLoad, toggleIsSubmitted, handleSelected}
    },
}
</script>