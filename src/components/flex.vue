<template>
  <section class="flex-container">
    <div class="content">1 ASD dsadsad dsadsadsa sadas d</div>
    <div>2 ASDdsads dsad as</div>
    <div>3 ASD dasdasdasd</div>
    <div>
      4 ASD dasdasdasdasd dasdsad sa dsadsa dsadasdsa dsadsa dsa d ds ad
      sadsadsdsda dsad
    </div>
  </section>
</template>

<script>
export default {};
</script>

<style lang="scss" scoped>
.flex-container {
  display: grid;
  grid-template-columns: 1fr 1fr 1fr 1fr;
  grid-gap: 10px;
  padding: 5px 5%;
  background-color: DodgerBlue;
  //min-height: 100vh;
  .content {
    color: chartreuse;
    font-weight: bold;
  }
}

.flex-container > div {
  background-color: #f1f1f1;
  border-radius: 15px;
  margin: 10px;
  text-align: center;
  line-height: 75px;
  font-size: 30px;
}
</style>
