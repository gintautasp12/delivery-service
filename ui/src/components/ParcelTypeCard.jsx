import React from "react";
import ImageBox from "./ImageBox";
import ButtonBase from "@material-ui/core/ButtonBase";

function ParcelTypeCard(props) {
  return (
    <ButtonBase className="w-100">
      <div className="form-inner form-inner-document w-100">
        <div className="form-header">{props.name}</div>
        <ImageBox image={props.image} alt={props.name} />
      </div>
    </ButtonBase>
  );
}

export default ParcelTypeCard;
