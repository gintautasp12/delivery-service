import React from "react";
import { Grid, Button } from "@material-ui/core";
import ParcelTypeCard from "./ParcelTypeCard";
import DocumentImage from "../images/letter.png";
import BoxImage from "../images/box.png";
import "../styles/SendingInfo.css";

function ParcelType(props) {
  return (
    <div className="form-wrapper">
      <Grid container justify="center" spacing={9}>
        <Grid item xs={6}>
          <ParcelTypeCard image={DocumentImage} name="Document" selectedPackageType={props.selectedPackageType} onClick={props.onChange} />
        </Grid>
        <Grid item xs={6}>
          <ParcelTypeCard image={BoxImage} name="Box" selectedPackageType={props.selectedPackageType} onClick={props.onChange} />
        </Grid>
      </Grid>
      <Button color="primary" variant="contained" className="form-button" onClick={props.NextPage}>
        Next
      </Button>
    </div>
  );
}

export default ParcelType;
