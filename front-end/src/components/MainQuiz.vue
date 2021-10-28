<template>
    <p v-if="fetchMsg">{{fetchMsg}}</p>
     <!-- Loading animation while waiting for API fetching  -->
    <div v-else-if="questions.length < 25" class='w-1/4 mx-auto my-0' id="loading-animation"></div>
    <!-- Main Quiz -->
    <section v-else-if="!isSubmitted && questions.length" class="w-full min-h-screen">
        <div v-if="isSubmitLoading" class="fixed z-50 grid w-screen h-screen bg-error-900 bg-opacity-70 place-items-center">
            <p class="text-2xl font-semibold text-center text-gray-50">Submitting your answer... Please do not close the tab!</p>
        </div>
        <!-- Top Navigation -->
        <nav class='relative flex items-center justify-between w-full px-6 lg:px-20 pt-11'>
           <img src="../assets/logis-base-logo.png" class="inline-block w-12 md:w-24 " alt="the-logisticom-logo">
            <!-- Time Countdown Animation ---->
            <TimeCount @toSubmit="submit" :isBtnSubmitted="isSubmitLoading"/>
            <button @click.prevent="submit" class="flex items-center gap-2 px-5 py-2 text-sm font-bold text-white transition-all duration-200 ease-linear rounded-full hover:bg-none hover:bg-error-900 md:py-3 md:text-base md:px-7 bg-gradient-to-r from-red-600 to-pink-600" :class="{loading: isSubmitLoading}">
                <div v-if="isSubmitLoading" class="inline-block w-4 h-4 ease-linear border-2 border-t-2 border-gray-200 rounded-full loader"></div>
                    Submit <svg width="24px" height="24px" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke="currentColor" color="#f9f9f9"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M14 5l7 7m0 0l-7 7m7-7H3"></path></svg>
            </button>
            <p v-if="submitError" class='absolute text-xs right-6 lg:right-20 top-full text-error-900'>Cannot submit. Please try again!</p>
        </nav>
       
        
        <!-- Question Number List ---->
        <div class="flex flex-wrap items-center px-6 mt-8 mb-10 lg:px-20 gap-x-9 gap-y-4 ">
            <!-- <QuestNumber v-for="questionItem in questionNumberList" :key="questionItem.number" :number="questionItem.number" @clickQuestion="handleQuestionClick" :isActive="questionItem.number == (currentQuestionIndex + 1)" :isDone="submitPayLoad.answers[questionItem.number-1].answer !== 'N/A'"/> -->
            <QuestNumber v-for="(questionItem, index) in questions" :key="questionItem.questionNumber" :number="questionItem.questionNumber" @clickQuestion="handleQuestionClick" :isActive="index == currentQuestionIndex" :isDone="submitPayLoad.answers[questionItem.questionNumber-1].answer !== 'N/A'"/>
        </div>


        <!-- Question Section---->        
         <div class="flex flex-col items-center w-full h-auto min-h-screen py-20 text-gray-900 rounded-5xl bg-form-bg form-container" style="border-radius: 80px 80px 0 0">
             


             <div class="relative w-10/12 md:w-8/12">
             <svg class="absolute block w-12 h-12 cursor-pointer -bottom-16 md:transform -left-3 md:-translate-y-1/2 md:-left-24 md:top-1/2 back-question lg:-left-40 md:w-16 md:h-16 lg:w-20 lg:h-20" :class="{'opacity-30' : currentQuestionIndex == 0}" preserveAspectRatio="none"  @click="toPreviousQuestion" width="84" height="84" viewBox="0 0 84 84" fill="none" xmlns="http://www.w3.org/2000/svg">
                <path d="M38.5 52.5L28 42L38.5 52.5ZM28 42L38.5 31.5L28 42ZM28 42H56H28ZM10.5 42C10.5 37.8634 11.3148 33.7672 12.8978 29.9455C14.4808 26.1237 16.8011 22.6512 19.7261 19.7261C22.6512 16.8011 26.1237 14.4808 29.9455 12.8978C33.7672 11.3148 37.8634 10.5 42 10.5C46.1366 10.5 50.2328 11.3148 54.0545 12.8978C57.8763 14.4808 61.3488 16.8011 64.2739 19.7261C67.1989 22.6512 69.5192 26.1237 71.1022 29.9455C72.6852 33.7672 73.5 37.8634 73.5 42C73.5 50.3543 70.1813 58.3665 64.2739 64.2739C58.3665 70.1813 50.3543 73.5 42 73.5C33.6457 73.5 25.6335 70.1813 19.7261 64.2739C13.8187 58.3665 10.5 50.3543 10.5 42V42Z" stroke="#757575" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"/>
            </svg>

            <svg class="absolute w-12 h-12 cursor-pointer md:transform md:-translate-y-1/2 -bottom-16 -right-3 md:-right-24 md:top-1/2 next-question lg:-right-40 md:w-16 md:h-16 lg:w-20 lg:h-20"  :class="{'opacity-30' : currentQuestionIndex == (questions.length - 1)}" preserveAspectRatio="none" @click="toNextQuestion"  width="84" height="84" viewBox="0 0 84 84" fill="none" xmlns="http://www.w3.org/2000/svg">
                <g id="next-question">
                <path id="Vector" d="M45.5 31.5L56 42L45.5 31.5ZM56 42L45.5 52.5L56 42ZM56 42H28H56ZM73.5 42C73.5 46.1366 72.6852 50.2328 71.1022 54.0545C69.5192 57.8763 67.1989 61.3488 64.2739 64.2739C61.3488 67.1989 57.8763 69.5192 54.0545 71.1022C50.2328 72.6852 46.1366 73.5 42 73.5C37.8634 73.5 33.7672 72.6852 29.9455 71.1022C26.1237 69.5192 22.6512 67.1989 19.7261 64.2739C16.8011 61.3488 14.4808 57.8763 12.8978 54.0545C11.3148 50.2328 10.5 46.1366 10.5 42C10.5 33.6457 13.8187 25.6335 19.7261 19.7261C25.6335 13.8187 33.6457 10.5 42 10.5C50.3543 10.5 58.3665 13.8187 64.2739 19.7261C70.1813 25.6335 73.5 33.6457 73.5 42Z" stroke="#757575" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"/>
                </g>
            </svg>
                <question-quiz class="mb-12 " :questionNumber="currentQuestionIndex +1" :questionText="questions[currentQuestionIndex].questionText">

                </question-quiz>

                <!-- Option List --->
                <div class='flex flex-col gap-5 '>
                    <Option v-for=" (choice, index) in questions[currentQuestionIndex].choices" :key="index" :optionText="choice.choiceText" :questionNumber="questions[currentQuestionIndex].questionNumber" :option="choice.option" :hasChecked="submitPayLoad.answers[currentQuestionIndex].answer == choice.option" @optionSelected ="handleSelected" />
                </div>
             </div>
        </div>


    </section>

   


    <Submit v-else :submitPayLoad="submitPayLoad"/>

</template>

<script>

import {computed, onMounted, ref, reactive, onUpdated, watchEffect} from 'vue'
import TimeCount from "./TimeCount.vue"
import QuestNumber from "./QuestNumber.vue"
import Option from "./Option.vue"
import Submit from "./Submit.vue"
import QuestionQuiz from "./QuestionQuiz.vue"
import QuizAPI from "../services/QuizAPI"
import lottie from "lottie-web";
import LoadingAnimation from '../assets/loading-animation.json'

export default {
    components: {TimeCount, QuestNumber, Option, Submit, QuestionQuiz},
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
        // const questionNumberList = ref([]);

        const initSubmitPayload = () => {
            
            questions.value.forEach((item, index) => {
                submitPayLoad.answers.splice(index, 0, {questionText: item.questionText, questionNumber: item.questionNumber, choiceText: 'N/A', answer: 'N/A'});
                

                
            });

        }

        const loadQuiz = async () => {
            try {
                let response = await QuizAPI.getQuiz(props.quizCode);
                questions.value = response.data.quiz.questions;
                
                //Test with local server
            //    let response = await QuizAPI.getLocalQuiz();
            //    questions.value = response.questions;
                

                 //Initialize the submit payload once questions.value are fetched.
                initSubmitPayload();
                

            } catch (err) {
                console.log(err.response);
                fetchMsg.value = "Oops, something wrong with the server. Please refresh the page!"
                
            }
        }
        loadQuiz();

        
        

        


        
        
        

        
        
       


        
        
        
        
        //handle any option selecting event change.
        const answer = ref('N/A');
        const handleSelected = (selection) => {
            
            answer.value = selection;
            submitPayLoad.answers[currentQuestionIndex.value].answer = answer.value.option;
            submitPayLoad.answers[currentQuestionIndex.value].choiceText =answer.value.optionText;
            
        }
             
        const handleQuestionClick = (el) => {
            
            currentQuestionIndex.value = parseInt(el.innerText) - 1;
            
        };

        const toNextQuestion = () => {

            if (currentQuestionIndex.value < (questions.value.length - 1) ){
 
                currentQuestionIndex.value+=1; 
                
                
            }
            
            
            
        }

        const toPreviousQuestion = () => {
            
            if (currentQuestionIndex.value > 0 ){
                
                currentQuestionIndex.value-=1; 
                
                
            }
            
        }


        const formattedAnwser = () => {
            return submitPayLoad.answers.map((item) => ({
                answer: item.answer === 'N/A'? null: item.answer,
                questionNumber: item.questionNumber
            }))
        }


        
        
        const isSubmitLoading = ref(false);
        const submitError = ref(false);
        
        const submit = () => {
            isSubmitLoading.value = true;
            
            const formattedPayLoad = {...submitPayLoad, answers: formattedAnwser()}
            QuizAPI.submitQuiz(formattedPayLoad)
            .then(response => {
                // console.log(response);
                if (response.status == 200) {
                    isSubmitted.value = true;
                    isSubmitLoading.value = false;
                }else{
                    console.log(response.statusText);
                    submitError.value = true;
                    isSubmitLoading.value = false;
                }
                
                
                })
            .catch(error => {
                console.log(error);
                isSubmitLoading.value = false;
                submitError.value = true;
                
                })
            

            //Test locally
            // isSubmitLoading.value = true;
            // setTimeout(() => {
            //     isSubmitLoading.value = false;
            //     isSubmitted.value = true;
            // }, 5000)
            
            
        }

    



       
        
        return{handleQuestionClick, questions, currentQuestionIndex/*, selectAns*/, toNextQuestion, toPreviousQuestion,  /*questionNumberList,checkValue, buttonText*/isSubmitted, submitPayLoad/*, toggleIsSubmitted*/, handleSelected, fetchMsg, submit, isSubmitLoading, submitError}
    },
}
</script>