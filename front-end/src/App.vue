<template>
    <main class="grid h-auto min-h-screen main-bg place-items-center">
              
        <!------ Login Form ----->
        <!-- <router-view/> -->
        <home></home>

        
    </main>
</template>

<script>
import Home from './views/Home.vue';

    export default {
        components: {Home},

        setup(){
            
             window.onbeforeunload = function(event) {
             event.preventDefault(); // required in some browsers
    event.returnValue = "";
    return "Are you sure you want to exit";
        }
        }
    }

</script>

<style scoped>
    
    
   
</style>