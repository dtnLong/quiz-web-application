<template>
  <section v-if="!isLogin" class="relative grid w-full h-screen place-items-center">
    <img src="../assets/logis-base-logo.png" class="absolute w-20 md:w-40 top-11" alt="the-logisticom-logo">
    <div class="flex flex-col items-center w-11/12 md:w-10/12 lg:w-8/12 pb-14 text-gray-50 rounded-xl bg-form-bg pt-9">
      <h1 class="mt-16 mb-8 text-3xl font-bold lg:text-5xl text-error-500">Round 1 Quiz</h1>
      <p v-if="error" class="font-semibold text-red-700">{{error}}</p>
      <form class="w-3/4 md:w-4/6">
        <div class='mb-8'>
          <label for="group" class='text-xl font-medium text-gray-700 '>Group Name:</label>
          <input class="block w-full px-8 py-4 mt-2 text-gray-900 rounded-full bg-input-bg" id="group" type="text" placeholder="group123" v-model.lazy="groupName">
        </div>
        <div  class='mb-12'>
          <label for="code" class='text-xl font-medium text-gray-700 '>Quiz Code:</label>
          <input class="block w-full px-8 py-4 mt-2 text-gray-900 rounded-full bg-input-bg" type="text" placeholder='quiz01' v-model.lazy="quizCode"></div>

          
      </form>
      <button class="flex items-center gap-4 py-3 text-2xl font-bold rounded-full px-7 bg-gradient-to-r from-red-600 to-pink-600" :class="{loading: isLoading}" @click.prevent="authenticate"> <div v-if="isLoading" class="inline-block w-4 h-4 ease-linear border-2 border-t-2 border-gray-200 rounded-full loader"></div> Start the quiz!
      <svg width="32px" height="32px" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke="currentColor" color="#f9f9f9"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M14 5l7 7m0 0l-7 7m7-7H3"></path></svg></button>
      <p class="px-2 mt-3 text-xs text-center text-gray-700 ">Forgot your quiz code? Please contact <a href="#" class="italic underline">thelogisticom.rmitbc@gmail.com</a> to retrieve another one</p>
    </div>
  </section>
  
  <MainQuiz :groupName="groupName" :quizCode="quizCode" v-else  />

  
</template>

<script >
import {ref, onMounted } from 'vue'
import LoginAPI from '../services/LoginAPI'
import MainQuiz from '../components/MainQuiz.vue'
import lottie from "lottie-web";
import LoadingAnimation from '../assets/loading-animation.json'
export default {
  components: {MainQuiz},
  
  setup() {

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


    const isLogin = ref(true);
    const isLoading = ref(false);
    const groupName = ref(null);
    const quizCode = ref(null);
    const error = ref(null);
    const authenticate = () => {
     if(!groupName.value) {
        
       error.value= 'Group name is required!';
    }
    else if (!quizCode.value) {
      error.value= 'Quiz code is required!';
    }
    else{
      isLoading.value = true;
      LoginAPI.auth({groupName:groupName.value, quizCode: quizCode.value})
      .then((response) => {
        if (response.data.success) {
          isLogin.value = true;
        }
        isLoading.value = false;
      }).catch(err => {
        error.value = err.response.data.errors[0].message; 
       
        isLoading.value = false});
    }
    }

    return {authenticate, groupName, quizCode, error, isLogin, isLoading}
  }

}

</script>

<style scoped>

</style>
