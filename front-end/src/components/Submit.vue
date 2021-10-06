<template>
    <!-- <span class="absolute cursor-pointer top-10 left-24" @click="backToQuiz" >Back to Quiz</span> -->
    <section v-if="!isLogOut" class="relative h-auto py-36">
        <img src="../assets/logis-base-logo.png" class="absolute w-20 transform -translate-x-1/2 md:w-40 left-1/2 top-11" alt="the-logisticom-logo">
        
             <h1 class='relative text-4xl font-bold text-center text-error-900'>Submitted <span class="absolute inline-block w-32 -top-1/2 left-1/2 " id="confetti-animation"></span>  </h1>
        <p class='px-2 mt-3 mb-10 text-xl font-medium text-center text-error-900 '>You have successfully submitted! Here is the preview of your answers:</p>

        <div class="w-11/12 h-auto mx-auto overflow-y-scroll text-gray-800 md:w-10/12 lg:w-9/12 py-14 rounded-3xl bg-form-bg">

            <div class='w-11/12 mx-auto md:w-10/12 lg:w-3/4'>
                <p class='mb-2 text-xl font-bold'>Group: <span class='text-error-500'>{{submitPayLoad.groupName}}</span></p>
                <p class='mb-8 text-xl font-bold'>Quiz Code: <span class= 'text-error-500'>{{submitPayLoad.quizCode}} </span></p>

                <div class="flex flex-col gap-4">
                    <div class='text-xl' v-for="({questionNumber, questionText, choiceText, answer}) in submitPayLoad.answers" :key="questionNumber">
                    <p><span class="font-bold">{{questionNumber}}.</span>  {{questionText}}</p>
                    <p class='font-bold'>
                        {{answer}}. {{choiceText}}
                    </p>
                </div>
                </div>
            
            
            </div>

            
            
        </div>
        <!-- <div class="flex justify-center mt-12">
                <button class="flex items-center gap-2 py-3 text-xl font-bold text-white rounded-full px-7 bg-gradient-to-r from-red-600 to-pink-600 " @click="isLogOut = true">Log Out<svg width="24px" height="24px" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke="currentColor" color="#f9f9f9"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M14 5l7 7m0 0l-7 7m7-7H3"></path></svg></button>  
            </div> -->
    
        
        
       
    </section>

    <!-- <router-view v-else/> -->
</template>

<script>
import {useRoute, useRouter} from 'vue-router'
import lottie from "lottie-web";
import Confetti from '../assets/confetti.json'
import Home from '../views/Home.vue'
import QuizAPI from '../services/QuizAPI';
import {onMounted, ref} from 'vue';
export default {

    props:{
        submitPayLoad: Object, 
        formattedPayLoad: Object},

    
    emits: ["backToQuiz"],
    setup( props, {emit}) {
        const route = useRoute();
        const router = useRouter();
        // console.log(props.submitPayLoad)
        const isLogOut = ref(false);
        
        // const formattedAnwser = () => {
        //     return props.submitPayLoad.answers.map((item) => ({
        //         answer: item.answer === 'N/A'? null: item.answer,
        //         questionNumber: item.questionNumber
        //     }))
        // }


        // const formattedPayLoad = {...props.submitPayLoad, answers: formattedAnwser()}
        // console.log(formattedPayLoad);
        onMounted(() => {
      lottie.loadAnimation({
                container: document.getElementById("confetti-animation"), 
                renderer: "svg",
                loop: false,
                autoplay: true,
                //path: "https://assets7.lottiefiles.com/packages/lf20_xz75lvff.json",
                animationData: Confetti,
            })
    })
        // const logOut = () => {
        //     router.go(0);
        // }

        // const submit = () => {
        //     QuizAPI.submitQuiz(props.formattedPayLoad)
        //     .then(response => console.log(response))
        //     .catch(error => console.log(error.response))
        // }
        
        // window.onbeforeunload = confirmExit;
        // function confirmExit() {
        //     //submit();
        //     return "Are you sure you want to exit?"
        // }
        
        return{isLogOut}
    },
}
</script>